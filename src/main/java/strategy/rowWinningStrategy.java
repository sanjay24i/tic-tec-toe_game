package strategy;

import models.Board;
import models.Move;

public class rowWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
