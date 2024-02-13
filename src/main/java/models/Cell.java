package models;

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

    public void printCell(){
        if(cellState == CellState.FILLED){
            System.out.print("|"+ player.getSymbol() +"|");
        }
        System.out.print( "|-|");
    }
}
