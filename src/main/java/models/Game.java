package models;
import exception.DuplicatePlayerSymbol;
import exception.MoreThenOneBotException;
import exception.PlyerAndBoardCountMismatchException;

import java.util.*;

public class Game {
    private List<Player> playerList;
    private Board board;
    private int nextPlayerIndex;
    private Player winningPlayer;
    private GameState gameState;

    private Game(int dimention, List<Player> playerList) {
        this.playerList = playerList;
        this.board = new Board(dimention);
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }
    public static class Builder {
        private int dimension;
        private List<Player> players;

        private Builder(){
            this.dimension = 0;
            this.players = new ArrayList<>();
        }

        public Builder setDimension(int dimension){
            this.dimension = dimension;
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
            return new Game(dimension, players);
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
