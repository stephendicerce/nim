import java.sql.SQLOutput;
import java.util.Scanner;

public class GameDebug {

    public static void main(String args[]) throws Exception{
        NimGame game = new NimGame();
        Scanner kb = new Scanner(System.in);
        String input;
        int numberOfCoins;
        boolean coinsSet;

        while(!game.getIsGameOver()) {
            coinsSet = false;
            numberOfCoins = 0;
            game.showCoinsLeft();

            do {
                System.out.println("Enter how many coins you would like to take between 1 and 3");
                input = kb.nextLine();
                try {
                    numberOfCoins = Integer.parseInt(input);
                    if(numberOfCoins>0 && numberOfCoins<4) {
                        coinsSet = true;
                    } else {
                        System.out.println("You didn't enter a correct amount of coins, try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You didn't enter a number, please try again.");
                }
            } while(!coinsSet);

            game.removeCoins(numberOfCoins);
            game.showCoinsLeft();
            game.aiTurn();
        }

        System.out.println("GAME OVER YOU LOSE!");
    }
}
