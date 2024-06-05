import controllers.GameController;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {

        System.out.println("Game is starting........");

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the dimension of the game : ");
        int dimension = scanner.nextInt();

        List<Player> players = new ArrayList<>();

        System.out.println("Will there be any bot ? y/n");
        String isBot = scanner.next();
        int noOfHumanPlayers = dimension-1;

        if(isBot.equals("y")){
            noOfHumanPlayers=dimension-2;

            System.out.println("Enter the name of the Bot: ");
            String name = scanner.next();

            System.out.println("Enter the symbol of the bot");
            String botSymbol = scanner.next();

            players.add(new Bot(botSymbol.charAt(0),name, BotDifficultyLevel.EASY));
        }

        for(int i=0; i<noOfHumanPlayers; i++){

            System.out.println("What is the name of the player " + (i+1));
            String name = scanner.next();

            System.out.println("What is the symbol of the player " + (i+1));
            String symbol = scanner.next();


           Player player = new Player(name, symbol.charAt(0), PlayerType.HUMAN);
           players.add(player);


        }

        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension,players);




        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){

            System.out.println("This is the current Board");
            gameController.displayBoard(game);

            System.out.println("do you want to undo y/n");
            String input = scanner.next();

            if(input.equals("y")){
                gameController.undo(game);
            }
            else{
                gameController.executeNextMove(game);
            }


        }

        if(gameController.getGameStatus(game).equals(GameStatus.DRAW)){
            System.out.println("GAME HAS DRAWN");
        }
        if(gameController.getGameStatus(game).equals(GameStatus.ENDED)){

            System.out.println("GAME ENDED");
            System.out.println("Winner is " + gameController.getWinner(game).getName());

        }



        }
    }
