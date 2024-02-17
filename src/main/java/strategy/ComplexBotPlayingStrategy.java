package strategy;

import models.*;

import java.util.List;

public class ComplexBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Cell makeMove(Board board) {
        List<List<Cell>> cellList = board.getBoard();
        int boardSize = board.getDimension();

        // Assuming the bot player is represented by a Player object
        Player botPlayer = new BotPlayer("Bot", 'X', PlayerType.BOT, DifficultyLevel.MIDIUM);

        // Check rows and columns for winning moves and blocking moves
        for (int i = 0; i < boardSize; i++) {
            List<Cell> row = cellList.get(i);
            List<Cell> column = StrategyUtils.getColumn(cellList, i);

            Cell winningMove = StrategyUtils.checkAndMakeMoveInLine(row, botPlayer);
            if (winningMove != null) {
                return winningMove;
            }

            winningMove = StrategyUtils.checkAndMakeMoveInLine(column, botPlayer);
            if (winningMove != null) {
                return winningMove;
            }
        }

        // Check diagonals for winning moves and blocking moves
        Cell winningMoveDiagonal = StrategyUtils.checkAndMakeMoveInLine(StrategyUtils.getMainDiagonal(cellList), botPlayer);
        if (winningMoveDiagonal != null) {
            return winningMoveDiagonal;
        }

        Cell winningMoveAntiDiagonal = StrategyUtils.checkAndMakeMoveInLine(StrategyUtils.getAntiDiagonal(cellList), botPlayer);
        if (winningMoveAntiDiagonal != null) {
            return winningMoveAntiDiagonal;
        }

        // Iterate through each cell to block potential wins
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (cellList.get(row).get(col).getCellState() == CellState.EMPTY) {
                    // Check if blocking is necessary for the current cell
                    if (isBlockingNecessary(cellList, row, col, botPlayer)) {
                        return cellList.get(row).get(col);
                    }
                }
            }
        }

        // Plan own winning moves
        for (int i = 0; i < boardSize; i++) {
            List<Cell> row = cellList.get(i);
            List<Cell> column = StrategyUtils.getColumn(cellList, i);

            Cell winningMoveRow = StrategyUtils.checkAndPlanWinningMove(row, botPlayer);
            if (winningMoveRow != null) {
                return winningMoveRow;
            }

            Cell winningMoveColumn = StrategyUtils.checkAndPlanWinningMove(column, botPlayer);
            if (winningMoveColumn != null) {
                return winningMoveColumn;
            }
        }

        // If no winning or blocking move is found, make a smart move
        return StrategyUtils.makeSmartMove(board, botPlayer);
    }

    // Check if blocking is necessary for the given cell
    private boolean isBlockingNecessary(List<List<Cell>> cellList, int row, int col, Player currentPlayer) {
        // Temporarily make a move for the opponent and check if it's a winning move
        cellList.get(row).get(col).setCellState(CellState.FILLED);
        cellList.get(row).get(col).setPlayer(currentPlayer);

        Cell opponentWinningMove = StrategyUtils.checkAndMakeMoveInLine(cellList.get(row), currentPlayer);
        if (opponentWinningMove == null) {
            opponentWinningMove = StrategyUtils.checkAndMakeMoveInLine(StrategyUtils.getColumn(cellList, col), currentPlayer);
        }
        if (opponentWinningMove == null) {
            opponentWinningMove = StrategyUtils.checkAndMakeMoveInLine(StrategyUtils.getMainDiagonal(cellList), currentPlayer);
        }
        if (opponentWinningMove == null) {
            opponentWinningMove = StrategyUtils.checkAndMakeMoveInLine(StrategyUtils.getAntiDiagonal(cellList), currentPlayer);
        }

        // Reset the cell to EMPTY after checking
        cellList.get(row).get(col).setCellState(CellState.EMPTY);
        cellList.get(row).get(col).setPlayer(null);

        // If the opponent has a winning move, blocking is necessary
        return opponentWinningMove != null;
    }

    // Other methods remain the same as in the previous code
}
