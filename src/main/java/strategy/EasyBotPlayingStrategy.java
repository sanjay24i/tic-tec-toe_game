package strategy;

import models.Board;
import models.Cell;
import models.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Cell makeMove(Board board) {
        List<List<Cell>> cellList = board.getBoard();

        for(List<Cell> row:cellList){
            for(Cell col : row){
                if(col.getCellState().equals(CellState.EMPTY)){
                    return col;
                }
            }
        }
        return null;
    }
}
