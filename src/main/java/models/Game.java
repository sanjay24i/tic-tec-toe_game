package models;
import exception.DuplicatePlayerSymbol;
import exception.MoreThenOneBotException;
import exception.PlyerAndBoardCountMismatchException;
import strategy.WinningStrategy;

import java.util.*;

public class Game {
    private List<Player> playerList;
    private Board board;
    private int nextPlayerIndex;
    private Player winningPlayer;
    private GameState gameState;
    private  List<Move> moves;

    private List<WinningStrategy> winningStrategies;



    private Game(int dimention, List<Player> playerList, List<WinningStrategy> winningStrategies) {
        this.playerList = playerList;
        this.board = new Board(dimention);
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player currentPlayer = playerList.get(nextPlayerIndex);
        Cell cell = currentPlayer.makeMove(board);
        Move move = new Move(cell, currentPlayer);
        moves.add(move);

        if(checkWinner(move, board)){
            gameState = GameState.CONCULUDED;
            winningPlayer = currentPlayer;
            return;
        }

        if(moves.size() == board.getDimension() * board.getDimension()){
            gameState = GameState.DRAW;
            return;
        }

        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % playerList.size();
    }

    private boolean checkWinner(Move move, Board board) {
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                return true;
            }
        }

        return  false;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        private List<WinningStrategy> winningStrategies;

        private Builder(){
            this.dimension = 0;
            this.players = new ArrayList<>();
        }

        public Builder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }

        public List<WinningStrategy> getWinningStrategy() {
            return winningStrategies;
        }

        public Builder setWinningStrategy(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }

        public void checkUniqueSymbolForPlayer() throws DuplicatePlayerSymbol {
            HashSet<Character> symbol = new HashSet<>();
            for(Player players : players){
                if(symbol.contains(players.getSymbol())){
                    throw new DuplicatePlayerSymbol();
                }
                symbol.add(players.getSymbol());
            }
        }

        public  void checkBotCount() throws MoreThenOneBotException {
            int playerCount = 0;
            for(Player players : players){
                if(players.getPlayerType() == PlayerType.BOT){
                    playerCount++;
                    if(playerCount > 1){
                        throw new MoreThenOneBotException();
                    }
                }
            }
        }

        public void checkPlayerCountAndBoardDimension() throws PlyerAndBoardCountMismatchException {
            if(players.size() != dimension-1){
                throw new PlyerAndBoardCountMismatchException();
            }
        }

        public Game build() throws DuplicatePlayerSymbol, MoreThenOneBotException, PlyerAndBoardCountMismatchException {
            checkUniqueSymbolForPlayer();
            checkBotCount();
            checkPlayerCountAndBoardDimension();
            return new Game(dimension, players, winningStrategies);
        }
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


}
