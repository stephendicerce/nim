import java.sql.SQLOutput;
import java.util.Scanner;

public class GameDebug {

    public static void main(String args[]) throws Exception{
        NimGame game;
        Scanner kb = new Scanner(System.in);
        String input;
        int numberOfCoins;
        boolean coinsSet;

        do {
            System.out.println("Welcome to Nim, press Enter to play a game or type \"quit\" to quit");
            input = kb.nextLine();

            if(!input.equals("quit")) {
                game = new NimGame();

                //amount of players choice
                System.out.println("Please enter \"1\" to play against the AI or \"2\" to play against a friend.");
                input = kb.nextLine();

                //playing against the AI
                if (input.equals("1")) {
                    while (!game.getIsGameOver()) {
                        coinsSet = false;
                        numberOfCoins = 0;
                        game.showCoinsLeft();

                        numberOfCoins = getNumberOfCoins();

                        game.removeCoins(numberOfCoins);
                        game.showCoinsLeft();
                        game.aiTurn();
                    }

                    System.out.println("GAME OVER YOU LOSE!");

                //playing with 2 players
                } else if (input.equals("2")) {
                    int playerNumber = 1;
                    while (!game.getIsGameOver()) {
                        coinsSet = false;
                        numberOfCoins = 0;
                        game.showCoinsLeft();

                        System.out.println("Player "+ playerNumber + "'s turn.");

                        numberOfCoins = getNumberOfCoins();

                        game.removeCoins(numberOfCoins);

                        if(playerNumber == 1) playerNumber = 2;
                        else playerNumber = 1;
                    }
                    if(playerNumber == 1) playerNumber = 2;
                    else playerNumber = 1;
                    System.out.println("Player " + playerNumber + " wins!");
                } else {
                    System.out.println("You entered an incorrect command try again");
                }
            }
        } while(!input.equals("quit"));
        System.out.println("Thanks for playing!");
    }

    /**
     * gets the desired number of coins to be taken from the current non-AI player
     * @return the number of coins to be taken out
     */
    static int getNumberOfCoins() {
        String input;
        Scanner kb = new Scanner(System.in);
        int numberOfCoins = 0;
        boolean coinsSet = false;

        do {
            System.out.println("Enter how many coins you would like to take between 1 and 3");
            input = kb.nextLine();
            try {
                numberOfCoins = Integer.parseInt(input);
                if (numberOfCoins > 0 && numberOfCoins < 4) {
                    coinsSet = true;
                } else {
                    System.out.println("You didn't enter a correct amount of coins, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a number, please try again.");
            }
        } while (!coinsSet);

        return numberOfCoins;

    }
}
