package models;
import Factories.BotPlyingStrategyFactory;
import strategy.BotPlayingStrategy;
public class BotPlayer extends Player{
    private DifficultyLevel difficultyLavel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(String name, char symbol, PlayerType playerType, DifficultyLevel difficultyLavel) {
        super(name, symbol, playerType);
        this.difficultyLavel = difficultyLavel;
        this.botPlayingStrategy = BotPlyingStrategyFactory.getPlayingStrategy(difficultyLavel);
    }

    public DifficultyLevel getDifficultyLavel() {
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
