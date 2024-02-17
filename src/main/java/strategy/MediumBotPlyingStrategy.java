package strategy;

import models.Board;
import models.Cell;
import models.CellState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MediumBotPlyingStrategy implements BotPlayingStrategy {

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

    private List<Cell> getColumn(List<List<Cell>> board, int col) {
        List<Cell> column = new ArrayList<>();
        for (List<Cell> row : board) {
            column.add(row.get(col));
        }
        return column;
    }
    @Override
    public Cell makeMove(Board board) {
        List<List<Cell>> cellList = board.getBoard();
        int boardSize = cellList.size();
        Cell winningMove = null;

        // Check rows and columns for winning moves and blocking moves
        for (int i = 0; i < boardSize; i++) {
            List<Cell> row = cellList.get(i);
            List<Cell> column = getColumn(cellList, i);

            winningMove = checkAndMakeMoveInLine(row);
            if (winningMove != null) {
                return winningMove;
            }

            winningMove = checkAndMakeMoveInLine(column);
            if (winningMove != null) {
                return winningMove;
            }
        }

        // Check diagonals for winning moves and blocking moves
        winningMove = checkAndMakeMoveInLine(getMainDiagonal(cellList));
        if (winningMove != null) {
            return winningMove;
        }

        winningMove = checkAndMakeMoveInLine(getAntiDiagonal(cellList));
        if (winningMove != null) {
            return winningMove;
        }

        // If no winning or blocking move is found, make a random move
        return getRandomMove(board);
    }

    // Check if a line has a winning or blocking move
    // Check if a line has a winning or blocking move
    private Cell checkAndMakeMoveInLine(List<Cell> line) {
        int countEmpty = 0;
        int countFilled = 0;
        Cell emptyCell = null;

        for (Cell cell : line) {
            if (cell.getCellState().equals(CellState.EMPTY)) {
                countEmpty++;
                emptyCell = cell;
            } else if (cell.getCellState().equals(CellState.FILLED)) {
                countFilled++;
            }
        }

        // If there is one empty cell and the rest are filled, make a move
        if (countEmpty == 1 && countFilled == line.size() - 1) {
            return emptyCell;
        }

        return null;
    }

    // Get a random move from the available empty cells
    private Cell getRandomMove(Board board) {
        List<Cell> emptyCells = new ArrayList<>();
        List<List<Cell>> cellList = board.getBoard();

        for (List<Cell> row : cellList) {
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    emptyCells.add(cell);
                }
            }
        }

        if (!emptyCells.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(emptyCells.size());
            return emptyCells.get(randomIndex);
        }

        return null;
    }

    // Other helper methods (getColumn, getMainDiagonal, getAntiDiagonal) remain the same as in the previous code.
}
