package models;

public class BotPlayer extends Player{


    private DifficultyLavel difficultyLavel;

    public BotPlayer(String name, char symbol, PlayerType playerType, DifficultyLavel difficultyLavel) {
        super(name, symbol, playerType);
        this.difficultyLavel = difficultyLavel;
    }

    public DifficultyLavel getDifficultyLavel() {
        return difficultyLavel;
    }
}
