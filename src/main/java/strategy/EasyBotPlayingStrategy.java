/* package strategy;

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
*/

package strategy;

import models.Board;
import models.Cell;
import models.CellState;

import java.util.ArrayList;
import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Cell makeMove(Board board) {
        List<List<Cell>> cellList = board.getBoard();
        int boardSize = cellList.size();

        // Check rows
        for (List<Cell> row : cellList) {
            if (checkAndMakeMoveInLine(row)) {
                return getLastEmptyCellInLine(row);
            }
        }

        // Check columns
        for (int col = 0; col < boardSize; col++) {
            if (checkAndMakeMoveInLine(getColumn(cellList, col))) {
                return getLastEmptyCellInLine(getColumn(cellList, col));
            }
        }

        // Check diagonals
        if (checkAndMakeMoveInLine(getMainDiagonal(cellList))) {
            return getLastEmptyCellInLine(getMainDiagonal(cellList));
        }

        if (checkAndMakeMoveInLine(getAntiDiagonal(cellList))) {
            return getLastEmptyCellInLine(getAntiDiagonal(cellList));
        }

        // If no winning move is found, make a random move
        for (List<Cell> row : cellList) {
            for (Cell col : row) {
                if (col.getCellState().equals(CellState.EMPTY)) {
                    return col;
                }
            }
        }

        return null;
    }

    // Check if a line has a winning move
    private boolean checkAndMakeMoveInLine(List<Cell> line) {
        int count = 0;
        for (Cell cell : line) {
            if (cell.getCellState().equals(CellState.EMPTY)) {
                count++;
            }
        }
        return count == 1;
    }

    // Get the last empty cell in a line
    private Cell getLastEmptyCellInLine(List<Cell> line) {
        for (Cell cell : line) {
            if (cell.getCellState().equals(CellState.EMPTY)) {
                return cell;
            }
        }
        return null;
    }

    // Get a specific column from the board
    private List<Cell> getColumn(List<List<Cell>> board, int col) {
        List<Cell> column = new ArrayList<>();
        for (List<Cell> row : board) {
            column.add(row.get(col));
        }
        return column;
    }

    // Get the main diagonal from the board
    private List<Cell> getMainDiagonal(List<List<Cell>> board) {
        List<Cell> diagonal = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            diagonal.add(board.get(i).get(i));
        }
        return diagonal;
    }

    // Get the anti-diagonal from the board
    private List<Cell> getAntiDiagonal(List<List<Cell>> board) {
        List<Cell> antiDiagonal = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            antiDiagonal.add(board.get(i).get(board.size() - 1 - i));
        }
        return antiDiagonal;
    }
}
