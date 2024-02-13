package models;

public abstract class  Player {
    private String name;
    private int id;
    private char symbol;
    private PlayerType playerType;

    public abstract Cell makeMove(Board board);

    public Player(String name, char symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public char getSymbol() {
        return symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }


}
