public class NimGame {

    private boolean coins[];
    private int aiCounter;
    private int nextCoinPosition;
    private boolean isAITurn;
    private boolean isGameOver;


    /**
     * Constructor for the game. Creates 12 coins and sets up everything for either a 1 player game or 2 player
     */
    NimGame() {
        coins = new boolean[12];
        int i = 0;
        for(boolean coin: coins) {

            coins[i] = true;
            ++i;
        }
        aiCounter = 0;
        nextCoinPosition = 0;
        isAITurn = false;
        isGameOver = false;
    }

    /**
     * returns the boolean value that determines whether or not the game is over or not
     * @return whether or not the game is over
     */
    boolean getIsGameOver() {
        return isGameOver;
    }

    /**
     * removes a coin from the collection of coins
     */
    private void removeCoin() {
        if(nextCoinPosition>=0 && nextCoinPosition<12) {
            coins[nextCoinPosition] = false;
            if (nextCoinPosition != 11) {
                ++nextCoinPosition;
            } else {
                //System.out.println("The Computer has won the game!");
                isGameOver = true;
            }
        } else {
            System.out.println("WOAH!");
        }
    }

    /**
     * removes a set number of coins from the collection by calling private method: removeCoin()
     * also tells the AI how many coins were taken from the collection so the AI can make a decision of
     * how many coins to take itself
     * @param numberOfCoins the number of coins to be removed from the collection of coins
     */
    void removeCoins(int numberOfCoins) {
        //System.out.println("THE NUMBER OF COINS BEING TAKEN IS " + numberOfCoins);
        if(numberOfCoins<4 && numberOfCoins>0) {
            for (int i = 0; i < numberOfCoins; ++i) {
                removeCoin();
            }
            if (!isAITurn)
                aiCounter += numberOfCoins;
        }
    }

    /**
     * The brain of the AI, however many coins the AI saw the user take will determine how many coins the AI
     * decides to take
     */
    void aiTurn() {
        //System.out.println("The ai saw you took " + aiCounter + " coin(s)");
        switch (aiCounter) {
            case 1:
                removeCoins(3);
                System.out.println("The Computer has taken 3 coins.");
                break;
            case 2:
                removeCoins(2);
                System.out.println("The Computer has taken 2 coins.");
                break;
            case 3:
                removeCoins(1);
                System.out.println("The Computer has taken 1 coin.");
                break;
            default:
                removeCoins(4);
                System.out.println("The Computer decided to cheat and took 4 coins.");
                break;
        }
        aiCounter = 0;
    }

    /**
     * visually shows how many coins are left in the collection
     */
    void showCoinsLeft() {
        for(boolean coin: coins) {
            System.out.print("|");
            if(coin) {
                System.out.print(" C ");
            } else {
                System.out.print("   ");
            }
        }
        System.out.println("|");
    }


}
