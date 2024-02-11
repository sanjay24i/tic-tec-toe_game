package models;
import java.util.ArrayList;
import java.util.List;
public class Board {
    private int size;
    private  List<List<Cell>> board;

    public Board(int size) {
        this.size = size;
        this.board = new ArrayList<>();
        createBoard(size);
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
        for(List<Cell> row : board){
            for(Cell col: row){
                col.printCell();
            }
            System.out.println();
        }
    }
}
