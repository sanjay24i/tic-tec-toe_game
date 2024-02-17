package Factories;

import models.DifficultyLavel;
import strategy.BotPlayingStrategy;
import strategy.ComplexBotPlayingStrategy;
import strategy.EasyBotPlayingStrategy;
import strategy.MediumBotPlyingStrategy;

public class BotPlyingStrategyFactory {

    public static BotPlayingStrategy getPlayingStrategy(DifficultyLavel difficultyLavel){
        switch (difficultyLavel){
            case MIDIUM:
                return new MediumBotPlyingStrategy();
            case COMPLEX:
                return new ComplexBotPlayingStrategy();
            default:
                return new EasyBotPlayingStrategy();
        }

    }
}
