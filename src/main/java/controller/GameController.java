package controller;

import exception.DuplicatePlayerSymbol;
import exception.MoreThenOneBotException;
import exception.PlyerAndBoardCountMismatchException;
import models.Game;
import models.Player;
import strategy.WinningStrategy;

import java.util.List;
public class GameController {
    public Game createGame(int dimension, List<Player> player,
                           List<WinningStrategy> winningStrategyList)
                        throws DuplicatePlayerSymbol, MoreThenOneBotException, PlyerAndBoardCountMismatchException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(player)
                .setWinningStrategy(winningStrategyList)
                .build();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public void printBoard(Game game){
        game.printBoard();
    }
}
