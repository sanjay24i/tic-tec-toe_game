package Factories;

import models.DifficultyLevel;
import strategy.BotPlayingStrategy;
import strategy.ComplexBotPlayingStrategy;
import strategy.EasyBotPlayingStrategy;
import strategy.MediumBotPlyingStrategy;

public class BotPlyingStrategyFactory {

    public static BotPlayingStrategy getPlayingStrategy(DifficultyLevel difficultyLavel){
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
