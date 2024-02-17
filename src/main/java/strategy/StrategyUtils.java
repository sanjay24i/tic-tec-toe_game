package strategy;

import models.Board;
import models.Cell;
import models.CellState;
import models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategyUtils {
    // Check if a line has a winning or blocking move
    // Check if a line has a winning or blocking move
    // Check if a line has a winning or blocking move
    // Check if a line has a winning or blocking move
    public static Cell checkAndMakeMoveInLine(List<Cell> line, Player currentPlayer) {
        int countEmpty = 0;
        int countPlayer = 0;
        Cell emptyCell = null;

        for (Cell cell : line) {
            if (cell.getCellState().equals(CellState.EMPTY)) {
                countEmpty++;
                emptyCell = cell;
            } else if (cell.getPlayer() == currentPlayer) {
                countPlayer++;
            }
        }

        // If there is one empty cell and the rest are the player's cells, make the move
        if (countEmpty == 1 && countPlayer == line.size() - 1) {
            return emptyCell;
        }

        return null;
    }

    // Get a specific column from the board
    public static List<Cell> getColumn(List<List<Cell>> board, int col) {
        List<Cell> column = new ArrayList<>();
        for (List<Cell> row : board) {
            column.add(row.get(col));
        }
        return column;
    }

    // Get the main diagonal from the board
    public static List<Cell> getMainDiagonal(List<List<Cell>> board) {
        List<Cell> diagonal = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            diagonal.add(board.get(i).get(i));
        }
        return diagonal;
    }

    // Get the anti-diagonal from the board
    public static List<Cell> getAntiDiagonal(List<List<Cell>> board) {
        List<Cell> antiDiagonal = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            antiDiagonal.add(board.get(i).get(board.size() - 1 - i));
        }
        return antiDiagonal;
    }

    // Get a random move from the available empty cells
    public static Cell getRandomMove(Board board) {
        List<Cell> emptyCells = new ArrayList<>();

        for (List<Cell> row : board.getBoard()) {
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

    // Check and make a move in a line for a given player


    // Make a smart move - prioritize center and corners
    public static Cell makeSmartMove(Board board, Player currentPlayer) {
        // Your implementation for a smart move
        // Prioritize center cell, corners, or other strategic moves
        // ...

        // For example, return a random move for now
        return getRandomMove(board);
    }

    // Check and plan own winning move in a line
    public static Cell checkAndPlanWinningMove(List<Cell> line, Player currentPlayer) {
        int countEmpty = 0;
        int countBot = 0;
        Cell emptyCell = null;

        for (Cell cell : line) {
            if (cell.getCellState().equals(CellState.EMPTY)) {
                countEmpty++;
                emptyCell = cell;
            } else if (cell.getPlayer() == currentPlayer) {
                countBot++;
            }
        }

        // If there is one empty cell and the rest are the bot's cells, plan the winning move
        if (countEmpty == 1 && countBot == line.size() - 1) {
            return emptyCell;
        }

        return null;
    }

    // Other methods in StrategyUtils

    // Get a random move on the board


    // Get the first empty cell on the board
    private static Cell getFirstEmptyCell(Board board) {
        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    return cell;
                }
            }
        }
        return null;
    }
}
