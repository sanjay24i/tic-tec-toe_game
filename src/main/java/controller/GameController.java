package controller;

import exception.DuplicatePlayerSymbol;
import exception.MoreThenOneBotException;
import exception.PlyerAndBoardCountMismatchException;
import models.Game;
import models.Player;
import java.util.List;
public class GameController {
    public Game createGame(int dimension, List<Player> player) throws DuplicatePlayerSymbol, MoreThenOneBotException, PlyerAndBoardCountMismatchException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(player)
                .build();
    }

    public void printBoard(Game game){
        game.printBoard();
    }
}
