package models;
import java.util.Scanner;

public class HumanPlayer extends Player{
    Scanner scanner;

    public HumanPlayer(String name, char symbol, PlayerType playerType, Scanner scanner) {
        super(name, symbol, playerType);
        this.scanner = scanner;
    }

    public  Cell calculateRow(int number, Board board){

        Cell cell = new Cell(0, number, null);

        int dimension = board.getDimension();
        int linearIndex = number - 1; // Adjusting to 0-based indexing

        int row = linearIndex / dimension;
        int col = linearIndex % dimension;

      //  System.out.println("row - " + row + ", col - " + col);

        cell.setRow(row);
        cell.setCol(col);
        return  cell;
    }

    @Override
    public Cell makeMove(Board board) {
        System.out.println(getName() + " its your turn to make the move");
        int number = scanner.nextInt();
        int row = calculateRow(number,board).getRow();
        int col = calculateRow(number, board).getCol();

        while(!validateRowAndCol(row, col, board)){
            System.out.println(getName() + " Invalid move please enter row and column");
            number = scanner.nextInt();
            row = calculateRow(number, board).getRow();
            col = calculateRow(number, board).getCol();
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
