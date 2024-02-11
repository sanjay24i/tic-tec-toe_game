package models;

import controller.GameController;
import exception.DuplicatePlayerSymbol;
import exception.MoreThenOneBotException;
import exception.PlyerAndBoardCountMismatchException;
import java.util.ArrayList;
import java.util.List;
public class App {
    public static void main(String[] args) throws DuplicatePlayerSymbol, MoreThenOneBotException, PlyerAndBoardCountMismatchException {
        GameController gameController = new GameController();
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player("sanjay", 'X', PlayerType.HUMAN));
        players.add(new BotPlayer("GPT", '0', PlayerType.BOT, DifficultyLavel.EASY));
        Game game = gameController.createGame(dimension, players);
        gameController.printBoard(game);
    }
}
