package models;
import java.util.ArrayList;
import java.util.List;
public class Board {
    public int getDimension() {
        return dimension;
    }

    private int dimension;
    private  List<List<Cell>> board;

    public List<List<Cell>> getBoard() {
        return board;
    }

    public Board(int dimension) {
        this.dimension = dimension;
        this.board = new ArrayList<>();
        createBoard(dimension);
    }

    public void createBoard(int size){
        for(int i = 0; i < size; i++){
            List<Cell> row= new ArrayList<>();
            for(int j =0; j < size; j++){
                row.add(new Cell(i, j, CellState.EMPTY));
            }

            board.add(row);
        }
    }

    public void printBoard(){
        int count = 1;
        System.out.flush();
        for(List<Cell> row : board){
            for(Cell col: row){
                col.printCell(count);
                count++;
            }
            System.out.println();
        }
    }
}
