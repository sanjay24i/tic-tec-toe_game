package models;
import Factories.BotPlyingStrategyFactory;
import strategy.BotPlayingStrategy;
public class BotPlayer extends Player{
    private DifficultyLavel difficultyLavel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(String name, char symbol, PlayerType playerType, DifficultyLavel difficultyLavel) {
        super(name, symbol, playerType);
        this.difficultyLavel = difficultyLavel;
        this.botPlayingStrategy = BotPlyingStrategyFactory.getPlayingStrategy(difficultyLavel);
    }

    public DifficultyLavel getDifficultyLavel() {
        return difficultyLavel;
    }

    @Override
    public Cell makeMove(Board board) {
        System.out.println("Bot is making the move ");
        Cell cell = botPlayingStrategy.makeMove(board);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }
}
