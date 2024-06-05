package factories;

import models.BotDifficultyLevel;
import strategies.botplayingstrategy.BotPlayingStrategy;
import strategies.botplayingstrategy.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactory {


    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel){


        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){

            return new EasyBotPlayingStrategy();

        }
        else{
            return null;
        }



    }



}
