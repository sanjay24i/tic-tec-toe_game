package models;
import java.util.Scanner;

public class HumanPlayer extends Player{
    Scanner scanner;

    public HumanPlayer(String name, char symbol, PlayerType playerType, Scanner scanner) {
        super(name, symbol, playerType);
        this.scanner = scanner;
    }

    @Override
    public Cell makeMove(Board board) {
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        System.out.print(getName() + " its your turn to make the move");
        while(!validateRowAndCol(row, col, board)){
            System.out.print(getName() + " Invalid move please enter row and column");
            row = scanner.nextInt();
            col = scanner.nextInt();
        }

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }

    private boolean validateRowAndCol(int row, int col, Board board) {
        if(row >= board.getDimension() || row < 0){
            return false;
        }

        if(col >= board.getDimension() || col < 0){
            return false;
        }

        if(CellState.FILLED.equals(board.getBoard().get(row).get(col).getCellState())){
            return false;
        }

        return true;
    }

    public HumanPlayer(String name, char symbol, PlayerType playerType) {
        super(name, symbol, playerType);
    }
}
