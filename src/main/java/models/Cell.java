package models;
import java.io.*;
public class Cell {
    private int row;
    private int col;
    private Player player;

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    private CellState cellState;

    public Cell(int row, int col, CellState cellState) {
        this.row = row;
        this.col = col;
        this.cellState = cellState;
    }

    public int getRow() {
        return row;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }

    public CellState getCellState() {
        return cellState;
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[38;2;58;53;53m";
    public void printCell(int count){
        String formatedCount = String.format("%02d", count);
        if (cellState.equals(CellState.FILLED)) {
            System.out.print("| "+player.getSymbol()+"  |");
        }
        else{
            System.out.print("| "+ ANSI_YELLOW + formatedCount + ANSI_RESET +" |");
        }
    }
}
