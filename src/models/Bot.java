package models;

import factories.BotPlayingStrategyFactory;
import strategies.botplayingstrategy.BotPlayingStrategy;
import strategies.botplayingstrategy.EasyBotPlayingStrategy;

public class Bot extends Player{

   private BotDifficultyLevel botDifficultyLevel;

   private BotPlayingStrategy botPlayingStrategy;

   public Bot(char symbol,String name, BotDifficultyLevel botDifficultyLevel) {
      super(name, symbol, PlayerType.BOT);  //call of the constructor of parent class must be the first statement and it means the object of the parent class should be created first and then we should continue to child class constructor.
      this.botDifficultyLevel = botDifficultyLevel;
      this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);


   }


   public BotDifficultyLevel getBotDifficultyLevel() {
      return botDifficultyLevel;
   }

   public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
      this.botDifficultyLevel = botDifficultyLevel;
   }

   @Override
   public Move decideMove(Board board){

      return botPlayingStrategy.decideMove(this, board);

   }
}
