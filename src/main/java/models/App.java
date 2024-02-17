package models;

import controller.GameController;
import exception.DuplicatePlayerSymbol;
import exception.MoreThenOneBotException;
import exception.PlyerAndBoardCountMismatchException;
import strategy.WinningStrategy;
import strategy.columnWinningStrategy;
import strategy.diagonalWinningStrategy;
import strategy.rowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws DuplicatePlayerSymbol, MoreThenOneBotException, PlyerAndBoardCountMismatchException {
        GameController gameController = new GameController();

        List<Player> players = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        List<WinningStrategy> winingStrategies = new ArrayList<>();
        winingStrategies.add(new rowWinningStrategy());
        winingStrategies.add(new columnWinningStrategy());
        winingStrategies.add(new diagonalWinningStrategy());
        System.out.println("How many player want to play");
        int playerCount = scanner.nextInt();
        scanner.nextLine();
        boolean isBotPlayerCreated = false;
        int dimension = playerCount + 1;
        int playerType = 1;
        int isBotPlyer = 1;

        System.out.println("Do you want Bot Player : press 1 for yes or 0 for No");
        isBotPlyer = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < playerCount; i++){
            System.out.println(players.size() + "- p -" + (playerCount-1) + "-" + (players.size() == (playerCount -1)));
            if(!isBotPlayerCreated  && isBotPlyer == 1) {
                // check if last player remain and bot is not selected skip message
                if((players.size())== (playerCount -1) && playerType == 1){
                    playerType = 0;
                }else{
                    System.out.println("Select Player Type : For Bot enter 0. For Human enter 1");
                    playerType = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                }

            }
            if(playerType == 0){
                System.out.println("Enter Bot Player Name");
                String  botName = scanner.nextLine();
                System.out.println("Enter Bot symbol single character");
                char  botSymbol = scanner.nextLine().charAt(0);
                players.add(new BotPlayer(botName, botSymbol, PlayerType.BOT, DifficultyLavel.COMPLEX));
                isBotPlayerCreated = true;
                playerType = 1;
                isBotPlyer = 0;
            }
            else{
                System.out.println("Enter " + (i+1) + " Player Name");
                String  playerName = scanner.nextLine();
               // scanner.nextLine();
                System.out.println("Enter " + (i+1) + " Player symbol single character");
                char  playerSymbol= scanner.nextLine().charAt(0);
                players.add(new HumanPlayer(playerName, playerSymbol, PlayerType.HUMAN, scanner));
            }
        }

        // players.add(new BotPlayer("GPT", '+', PlayerType.BOT, DifficultyLavel.EASY));
        Game game = gameController.createGame(dimension, players, winingStrategies);

        while(GameState.IN_PROG.equals(game.getGameState())){

            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        if(GameState.CONCULUDED.equals(game.getGameState())){
            gameController.printBoard(game);
            System.out.print(game.getWinningPlayer().getName() + " has won the game");
        }

        if(GameState.DRAW.equals(game.getGameState())){
            gameController.printBoard(game);
            System.out.print(" Its a draw");
        }
    }
}
