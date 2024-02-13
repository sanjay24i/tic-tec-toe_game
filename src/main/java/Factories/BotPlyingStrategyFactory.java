package Factories;

import models.DifficultyLavel;
import strategy.BotPlayingStrategy;
import strategy.EasyBotPlayingStrategy;

public class BotPlyingStrategyFactory {

    public static BotPlayingStrategy getPlayingStrategy(DifficultyLavel difficultyLavel){
        return new EasyBotPlayingStrategy();
    }
}
