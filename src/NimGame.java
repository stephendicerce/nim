public class NimGame {

    private boolean coins[];
    private int aiCounter;
    private int nextCoinPosition;
    private boolean isAITurn;
    private boolean isGameOver;


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

    boolean getIsGameOver() {
        return isGameOver;
    }

    private void removeCoin() {
        if(nextCoinPosition>=0 && nextCoinPosition<12) {
            coins[nextCoinPosition] = false;
            if (nextCoinPosition != 11) {
                ++nextCoinPosition;
            } else {
                System.out.println("The Computer has won the game!");
                isGameOver = true;
            }
        } else {
            System.out.println("WOAH!");
        }
    }

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
