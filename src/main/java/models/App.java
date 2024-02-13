package models;

import controller.GameController;
import exception.DuplicatePlayerSymbol;
import exception.MoreThenOneBotException;
import exception.PlyerAndBoardCountMismatchException;
import strategy.WinningStrategy;
import strategy.columnWinningStrategy;
import strategy.diagonalWinningStrategy;
import strategy.rowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws DuplicatePlayerSymbol, MoreThenOneBotException, PlyerAndBoardCountMismatchException {
        GameController gameController = new GameController();
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        List<WinningStrategy> winingStrategies = new ArrayList<>();
        winingStrategies.add(new rowWinningStrategy());
        winingStrategies.add(new columnWinningStrategy());
        winingStrategies.add(new diagonalWinningStrategy());
        players.add(new HumanPlayer("sanjay", 'X', PlayerType.HUMAN, scanner));
        players.add(new BotPlayer("GPT", '0', PlayerType.BOT, DifficultyLavel.EASY));
        Game game = gameController.createGame(dimension, players, winingStrategies);

        while(GameState.IN_PROG.equals(game.getGameState())){
            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        if(GameState.CONCULUDED.equals(game.getGameState())){
            System.out.print(game.getWinningPlayer().getName() + "has won the game");
        }

        if(GameState.DRAW.equals(game.getGameState())){
            System.out.print("Its a draw");
        }
    }
}
