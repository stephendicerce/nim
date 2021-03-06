import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

public class NimApp extends Application {

    private static int numberOfCoins = 0;
    private static boolean newGameNeeded = false;
    private static NimGame game;
    private static boolean isAITurn = false;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Nim");
        Scene singlePlayerScene;
        Scene multiplayerScene;
        int depth = 70;

        //WELCOME SCREEN MENU




        //One Player Scene----------------------------------------------------------------------------------------------

        game = new NimGame();
        //images for the user and AI
        Image userImage = new Image("/images/user_image.png", 100, 100, false, true);
        Image aiImage = new Image("/images/ai_image.jpg", 100, 100, false, true);
        Image coinOne = new Image("/images/coin.png", 50, 50, false, true);
        Image coinTwo = new Image("/images/coin.png", 50, 50, false, true);
        Image coinThree = new Image("/images/coin.png", 50, 50, false, true);
        Image coinFour = new Image("/images/coin.png", 50, 50, false, true);
        Image coinFive = new Image("/images/coin.png", 50, 50, false, true);
        Image coinSix = new Image("/images/coin.png", 50, 50, false, true);
        Image coinSeven = new Image("/images/coin.png", 50, 50, false, true);
        Image coinEight = new Image("/images/coin.png", 50, 50, false, true);
        Image coinNine = new Image("/images/coin.png", 50, 50, false, true);
        Image coinTen = new Image("/images/coin.png", 50, 50, false, true);
        Image coinEleven = new Image("/images/coin.png", 50, 50, false, true);
        Image coinTwelve = new Image("/images/coin.png", 50, 50, false, true);

        //Image views for all images
        ImageView userImageView = new ImageView(userImage);
        ImageView aiImageView = new ImageView(aiImage);
        ImageView coinOneView = new ImageView(coinOne);
        ImageView coinTwoView = new ImageView(coinTwo);
        ImageView coinThreeView = new ImageView(coinThree);
        ImageView coinFourView = new ImageView(coinFour);
        ImageView coinFiveView = new ImageView(coinFive);
        ImageView coinSixView = new ImageView(coinSix);
        ImageView coinSevenView = new ImageView(coinSeven);
        ImageView coinEightView = new ImageView(coinEight);
        ImageView coinNineView = new ImageView(coinNine);
        ImageView coinTenView = new ImageView(coinTen);
        ImageView coinElevenView = new ImageView(coinEleven);
        ImageView coinTwelveView = new ImageView(coinTwelve);

        //buttons
        Button endTurnButton = new Button("End Turn");

        //other components
        DropShadow userShadow = new DropShadow();
        userShadow.setColor(Color.GREEN);
        userShadow.setOffsetX(0f);
        userShadow.setOffsetY(0f);
        userShadow.setWidth(depth);
        userShadow.setHeight(70);

        DropShadow noShadow = new DropShadow();
        noShadow.setColor(Color.BLACK);
        noShadow.setOffsetY(0f);
        noShadow.setOffsetX(0f);
        noShadow.setWidth(0);
        noShadow.setHeight(0);

        userImageView.setEffect(userShadow);

        Text numberOfCoinsText = new Text("Number Of Coins Taken:");
        Text coins = new Text();
        coins.setText("" + numberOfCoins);
        Text invalidSelectionText = new Text("You can only select between 1-3 coins");
        invalidSelectionText.setVisible(false);

        //menu
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem backToWelcome = new MenuItem("Back to Welcome Screen"); // actions for this menu item can be found at the bottom of this method
        MenuItem startOver = new MenuItem("Start Over");

        startOver.setOnAction(event -> {
            resetVisibility(coinOneView, coinTwoView, coinThreeView, coinFourView, coinFiveView, coinSixView, coinSevenView, coinEightView, coinNineView, coinTenView, coinElevenView, coinTwelveView, invalidSelectionText);
            game = new NimGame();
            resetNumberOfCoins();
            coins.setText(""+numberOfCoins);
        });

        menuFile.getItems().addAll(backToWelcome, startOver);


        menuBar.getMenus().add(menuFile);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        //components positioning
        HBox avatarBox = new HBox(100, userImageView, aiImageView);
        HBox textBox = new HBox(5, numberOfCoinsText, coins);
        HBox singlePlayerButtonBox = new HBox(100, textBox, endTurnButton);
        VBox textButtonContainerBox = new VBox(5, singlePlayerButtonBox, invalidSelectionText);
        HBox coinBox1 = new HBox(50, coinOneView, coinTwoView, coinThreeView, coinFourView, coinFiveView, coinSixView);
        HBox coinBox2 = new HBox(50, coinSevenView, coinEightView, coinNineView, coinTenView, coinElevenView, coinTwelveView);
        VBox vCoinBox = new VBox(25, coinBox1, coinBox2);
        VBox singlePlayerBox = new VBox(50, avatarBox, textButtonContainerBox, vCoinBox);

        avatarBox.setAlignment(Pos.CENTER);
        singlePlayerButtonBox.setAlignment(Pos.CENTER);
        coinBox1.setAlignment(Pos.CENTER);
        coinBox2.setAlignment(Pos.CENTER);
        vCoinBox.setAlignment(Pos.CENTER);
        singlePlayerBox.setAlignment(Pos.CENTER);
        textButtonContainerBox.setAlignment(Pos.CENTER);



        //button actions
        endTurnButton.setOnAction(event -> {
            isAITurn = true;
            if (!getNewGameNeeded()) {
                userImageView.setEffect(noShadow);
                aiImageView.setEffect(userShadow);
                invalidSelectionText.setVisible(false);
                game.removeCoins(numberOfCoins);
                int aiCoins = game.aiTurn();


                Timeline removeCoinsTimeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    private int i=0;

                    @Override
                    public void handle(ActionEvent event) {
                        if (i < aiCoins) {
                            //System.out.println("i= " + i);
                            if (i == 0) {

                                //System.out.println("inside initial reset");
                                resetNumberOfCoins();

                            }
                            aiImageView.setEffect(userShadow);
                            userImageView.setEffect(noShadow);
                            coins.setText("" + numberOfCoins);
                            increaseNumberOfCoins();
                            coins.setText("" + numberOfCoins);

                            if (coinOneView.isVisible()) {
                                coinOneView.setVisible(false);
                            } else if (coinTwoView.isVisible()) {
                                coinTwoView.setVisible(false);
                            } else if (coinThreeView.isVisible()) {
                                coinThreeView.setVisible(false);
                            } else if (coinFourView.isVisible()) {
                                coinFourView.setVisible(false);
                            } else if (coinFiveView.isVisible()) {
                                coinFiveView.setVisible(false);
                            } else if (coinSixView.isVisible()) {
                                coinSixView.setVisible(false);
                            } else if (coinSevenView.isVisible()) {
                                coinSevenView.setVisible(false);
                            } else if (coinEightView.isVisible()) {
                                coinEightView.setVisible(false);
                            } else if (coinNineView.isVisible()) {
                                coinNineView.setVisible(false);
                            } else if (coinTenView.isVisible()) {
                                coinTenView.setVisible(false);
                            } else if (coinElevenView.isVisible()) {
                                coinElevenView.setVisible(false);
                            } else if (coinTwelveView.isVisible()) {
                                coinTwelveView.setVisible(false);
                            }
                            //System.out.println("Took a coin");
                            if (i == (aiCoins-1)) {
                                //System.out.println("coins taken: " + numberOfCoins);
                                coins.setText("" + numberOfCoins);

                            }
                        }
                        if(i==aiCoins){
                            resetNumberOfCoins();
                            coins.setText(""+numberOfCoins);
                            userImageView.setEffect(userShadow);
                            aiImageView.setEffect(noShadow);
                            isAITurn = false;
                        }
                        ++i;
                    }
                }));
                removeCoinsTimeline.setCycleCount(aiCoins+1);
                removeCoinsTimeline.play();





                if (game.getIsGameOver()) {
                    invalidSelectionText.setText("GAME OVER!");
                    invalidSelectionText.setVisible(true);
                    toggleNewGameNeeded();
                    endTurnButton.setText("Play Again");
                }
            } else {
                game = new NimGame();
                invalidSelectionText.setVisible(false);
                invalidSelectionText.setText("You can only select between 1-3 coins");
                endTurnButton.setText("End Turn");
                coinOneView.setVisible(true);
                coinTwoView.setVisible(true);
                coinThreeView.setVisible(true);
                coinFourView.setVisible(true);
                coinFiveView.setVisible(true);
                coinSixView.setVisible(true);
                coinSevenView.setVisible(true);
                coinEightView.setVisible(true);
                coinNineView.setVisible(true);
                coinTenView.setVisible(true);
                coinElevenView.setVisible(true);
                coinTwelveView.setVisible(true);
                toggleNewGameNeeded();
                isAITurn = false;

            }
        });

        //coin actions
        coinOneView.setOnMouseClicked(event -> {
            if (takeCoin(coinOneView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinTwoView.setOnMouseClicked(event -> {
            if (takeCoin(coinTwoView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinThreeView.setOnMouseClicked(event -> {
            if (takeCoin(coinThreeView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinFourView.setOnMouseClicked(event -> {
            if (takeCoin(coinFourView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinFiveView.setOnMouseClicked(event -> {
            if (takeCoin(coinFiveView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinSixView.setOnMouseClicked(event -> {
            if (takeCoin(coinSixView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinSevenView.setOnMouseClicked(event -> {
            if (takeCoin(coinSevenView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinEightView.setOnMouseClicked(event -> {
            if (takeCoin(coinEightView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinNineView.setOnMouseClicked(event -> {
            if (takeCoin(coinNineView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinTenView.setOnMouseClicked(event -> {
            if (takeCoin(coinTenView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinElevenView.setOnMouseClicked(event -> {
            if (takeCoin(coinElevenView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        coinTwelveView.setOnMouseClicked(event -> {
            if (takeCoin(coinTwelveView)) {
                coins.setText("" + numberOfCoins);
            } else {
                invalidSelectionText.setVisible(true);
            }
        });

        BorderPane onePlayerRoot = new BorderPane();

        onePlayerRoot.setTop(menuBar);
        onePlayerRoot.setCenter(singlePlayerBox);

        singlePlayerScene = new Scene(onePlayerRoot, 850, 450, Color.WHITE);


        //Two Player Scene----------------------------------------------------------------------------------------------
        game = new NimGame();

        //images
        Image firstPlayerImage = new Image("/images/user_image.png", 100, 100, false, true);
        Image secondPlayerImage = new Image("/images/user_2_image.png", 100, 100, false, true);
        Image coin1 = new Image("images/coin.png", 50 , 50, false, true);
        Image coin2 = new Image("images/coin.png", 50, 50, false, true);
        Image coin3 = new Image("images/coin.png", 50, 50, false, true);
        Image coin4 = new Image("images/coin.png", 50, 50, false, true);
        Image coin5 = new Image("images/coin.png", 50, 50, false, true);
        Image coin6 = new Image("images/coin.png", 50, 50, false, true);
        Image coin7 = new Image("images/coin.png", 50, 50, false, true);
        Image coin8 = new Image("images/coin.png", 50, 50, false, true);
        Image coin9 = new Image("images/coin.png", 50, 50, false, true);
        Image coin10 = new Image("images/coin.png", 50, 50, false, true);
        Image coin11 = new Image("images/coin.png", 50, 50, false, true);
        Image coin12 = new Image("images/coin.png", 50, 50, false, true);



        //image views
        ImageView fpImageView = new ImageView(firstPlayerImage);
        ImageView spImageView = new ImageView(secondPlayerImage);
        ImageView coin1View = new ImageView(coin1);
        ImageView coin2View = new ImageView(coin2);
        ImageView coin3View = new ImageView(coin3);
        ImageView coin4View = new ImageView(coin4);
        ImageView coin5View = new ImageView(coin5);
        ImageView coin6View = new ImageView(coin6);
        ImageView coin7View = new ImageView(coin7);
        ImageView coin8View = new ImageView(coin8);
        ImageView coin9View = new ImageView(coin9);
        ImageView coin10View = new ImageView(coin10);
        ImageView coin11View = new ImageView(coin11);
        ImageView coin12View = new ImageView(coin12);


        //buttons
        Button switchPlayerButton = new Button("End Turn");


        //effects
        DropShadow currentPlayerShadow = new DropShadow();
        DropShadow hiddenShadow = new DropShadow();

        currentPlayerShadow.setColor(Color.GREEN);
        currentPlayerShadow.setHeight(70);
        currentPlayerShadow.setWidth(70);
        currentPlayerShadow.setOffsetX(0f);
        currentPlayerShadow.setOffsetY(0f);

        hiddenShadow.setColor(Color.WHITE);
        hiddenShadow.setHeight(70);
        hiddenShadow.setWidth(70);
        hiddenShadow.setOffsetX(0f);
        hiddenShadow.setOffsetY(0f);

        fpImageView.setEffect(currentPlayerShadow);
        spImageView.setEffect(hiddenShadow);

        //text
        final Text numberOfCoinsCaption = new Text("Number of Coins Taken:");
        Text amountOfCoins = new Text(""+numberOfCoins);
        Text checkAmountOfCoinsText = new Text("You can only select between 1-3 coins");
        checkAmountOfCoinsText.setVisible(false);

        //components
        BorderPane twoPlayerBorderPane = new BorderPane();
        HBox twoPlayerAvatarBox = new HBox(100, fpImageView, spImageView);
        HBox numberOfCoinsBox = new HBox(5, numberOfCoinsCaption, amountOfCoins);
        HBox multiPlayerButtonBox = new HBox(100, numberOfCoinsBox, switchPlayerButton);
        VBox textButtonAndSituationalTextBox = new VBox(5, multiPlayerButtonBox, checkAmountOfCoinsText);
        HBox coinRow1 = new HBox(50, coin1View, coin2View, coin3View, coin4View, coin5View, coin6View);
        HBox coinRow2 = new HBox(50, coin7View, coin8View, coin9View, coin10View, coin11View, coin12View);
        VBox coinContainer = new VBox(25, coinRow1, coinRow2);
        VBox multiPlayerBox = new VBox(50, twoPlayerAvatarBox, textButtonAndSituationalTextBox, coinContainer);

        twoPlayerAvatarBox.setAlignment(Pos.CENTER);
        numberOfCoinsBox.setAlignment(Pos.CENTER);
        multiPlayerButtonBox.setAlignment(Pos.CENTER);
        textButtonAndSituationalTextBox.setAlignment(Pos.CENTER);
        coinRow1.setAlignment(Pos.CENTER);
        coinRow2.setAlignment(Pos.CENTER);
        coinContainer.setAlignment(Pos.CENTER);
        multiPlayerBox.setAlignment(Pos.CENTER);


        //menu
        MenuBar twoPlayerMenuBar = new MenuBar();
        Menu twoPlayerFile = new Menu("File");
        MenuItem twoPlayerBackToWelcome = new MenuItem("Back to Welcome Screen");
        MenuItem twoPlayerStartOver = new MenuItem("Start Over");

        twoPlayerFile.getItems().addAll(twoPlayerBackToWelcome, twoPlayerStartOver);
        twoPlayerMenuBar.getMenus().addAll(twoPlayerFile);



        twoPlayerBorderPane.setTop(twoPlayerMenuBar);
        twoPlayerBorderPane.setCenter(multiPlayerBox);

        //button actions

        switchPlayerButton.setOnAction(event -> {
            if(!getNewGameNeeded()) {
                if(isAITurn) {
                    fpImageView.setEffect(hiddenShadow);
                    spImageView.setEffect(currentPlayerShadow);
                } else {
                    fpImageView.setEffect(currentPlayerShadow);
                    spImageView.setEffect(hiddenShadow);
                }

                checkAmountOfCoinsText.setVisible(false);
                game.removeCoins(numberOfCoins);
                resetNumberOfCoins();


                if(game.getIsGameOver()) {
                    if(isAITurn) {
                        checkAmountOfCoinsText.setText("GAME OVER! Player 2 Wins!");
                    } else {
                        checkAmountOfCoinsText.setText("GAME OVER! Player 1 Wins!");
                    }
                }

                isAITurn ^= true;
            } else {
                game = new NimGame();

            }
        });

        multiplayerScene = new Scene(twoPlayerBorderPane, 850, 450, Color.AQUA);


        //Menu Scene----------------------------------------------------------------------------------------------------

        //the different components of the welcome screen
        Label welcomeLabel = new Label("Welcome to Nim");
        Button onePlayerButton = new Button("One Player");
        Button twoPlayerButton = new Button("Two Players");
        BorderPane root = new BorderPane();
        Image cornerImage = new Image("/images/coins_edited.jpg", true);
        ImageView cImageView = new ImageView(cornerImage);


        //setting the location of the components
        HBox buttonBox = new HBox(50, onePlayerButton, twoPlayerButton);
        VBox container = new VBox(100, welcomeLabel, buttonBox);
        buttonBox.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);


        //adding actions to components
        onePlayerButton.setOnAction(e -> primaryStage.setScene(singlePlayerScene));
        twoPlayerButton.setOnAction(event -> primaryStage.setScene(multiplayerScene));

        Image corneImage = new Image("/images/coins_edited.jpg");

        Scene welcomeScene = new Scene(root, 400, 200, Color.WHITE);

        //root.setTop(menuBar);
        root.setCenter(container);


        //menu functionality
        backToWelcome.setOnAction(event -> {
            primaryStage.setScene(welcomeScene);
            game = new NimGame();
            resetVisibility(coinOneView, coinTwoView, coinThreeView, coinFourView, coinFiveView, coinSixView, coinSevenView, coinEightView, coinNineView, coinTenView, coinElevenView, coinTwelveView, invalidSelectionText);
            resetNumberOfCoins();
            coins.setText(""+numberOfCoins);
        });

        twoPlayerBackToWelcome.setOnAction(event -> {
            primaryStage.setScene(welcomeScene);
            resetVisibility(coin1View, coin2View, coin3View, coin4View, coin5View, coin6View, coin7View, coin8View, coin9View, coin10View, coin11View, coin12View, checkAmountOfCoinsText);
            resetNumberOfCoins();
            amountOfCoins.setText(""+numberOfCoins);
            game = new NimGame();
        });

        primaryStage.setScene(welcomeScene);

        primaryStage.show();

    }

    /**
     *
     * @return
     */
    private static boolean checkTurnStatus() {
        if(numberOfCoins<3) return true;
        else return false;
    }

    /**
     *
     */
    private static void increaseNumberOfCoins() {
        ++numberOfCoins;
    }

    /**
     *
     */
    private static void resetNumberOfCoins() {
        numberOfCoins = 0;
    }

    /**
     *
     * @return
     */
    private static boolean getNewGameNeeded() { return newGameNeeded; }

    /**
     *
     */
    private static void toggleNewGameNeeded() {
        if(newGameNeeded) newGameNeeded = false;
        else newGameNeeded = true;
    }

    /**
     * 
     * @param imageView
     * @return
     */
    private static boolean takeCoin(ImageView imageView) {

        if(checkTurnStatus() && !isAITurn) {
            imageView.setVisible(false);
            increaseNumberOfCoins();
            return true;
        } else {
            return false;
        }
    }


    private static void resetVisibility(ImageView coinOneView, ImageView coinTwoView, ImageView coinThreeView, ImageView coinFourView, ImageView coinFiveView, ImageView coinSixView, ImageView coinSevenView, ImageView coinEightView, ImageView coinNineView, ImageView coinTenView, ImageView coinElevenView, ImageView coinTwelveView, Text invalidSelectionText) {
        coinOneView.setVisible(true);
        coinTwoView.setVisible(true);
        coinThreeView.setVisible(true);
        coinFourView.setVisible(true);
        coinFiveView.setVisible(true);
        coinSixView.setVisible(true);
        coinSevenView.setVisible(true);
        coinEightView.setVisible(true);
        coinNineView.setVisible(true);
        coinTenView.setVisible(true);
        coinElevenView.setVisible(true);
        coinTwelveView.setVisible(true);
        invalidSelectionText.setVisible(false);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
