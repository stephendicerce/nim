import java.sql.SQLOutput;
import java.util.Scanner;

public class GameDebug {

    public static void main(String args[]) throws Exception{
        NimGame game;
        Scanner kb = new Scanner(System.in);
        String input;
        int numberOfCoins;
        boolean coinsSet;

        System.out.println("Please enter \"1\" to play against the AI or \"2 to play against a friend.");
        input = kb.nextLine();

        if(input.equals("1")) {
            while (!game.getIsGameOver()) {
                coinsSet = false;
                numberOfCoins = 0;
                game.showCoinsLeft();

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

                game.removeCoins(numberOfCoins);
                game.showCoinsLeft();
                game.aiTurn();
            }

            System.out.println("GAME OVER YOU LOSE!");
        } else if(input.equals("2")) {

        } else {
            System.out.println("You entered an incorrect command try again");
        }
    }
}
