import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class NimApp extends Application {

    static int numberOfCoins = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Nim");
        Scene singlePlayerScene;


        //One Player Scene----------------------------------------------------------------------------------------------

        NimGame game = new NimGame();
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

        List<ImageView> coinsList = new ArrayList<>();
        coinsList.add(coinOneView);
        coinsList.add(coinTwoView);
        coinsList.add(coinThreeView);
        coinsList.add(coinFourView);
        coinsList.add(coinFiveView);
        coinsList.add(coinSixView);
        coinsList.add(coinSevenView);
        coinsList.add(coinEightView);
        coinsList.add(coinNineView);
        coinsList.add(coinTenView);
        coinsList.add(coinElevenView);
        coinsList.add(coinTwelveView);

        //buttons
        Button endTurnButton = new Button("End Turn");

        //other components
        Text numberOfCoinsText = new Text("Number Of Coins Taken:");
        Text coins = new Text();
        coins.setText(""+numberOfCoins);

        //components positioning
        HBox avatarBox = new HBox(100, userImageView, aiImageView);
        HBox textBox = new HBox(5, numberOfCoinsText, coins);
        HBox singlePlayerButtonBox = new HBox(100, textBox, endTurnButton);
        HBox coinBox1 = new HBox(50, coinOneView, coinTwoView, coinThreeView, coinFourView, coinFiveView, coinSixView);
        HBox coinBox2 = new HBox(50, coinSevenView, coinEightView, coinNineView, coinTenView, coinElevenView, coinTwelveView);
        VBox vCoinBox = new VBox(25, coinBox1, coinBox2);
        VBox singlePlayerBox = new VBox(50, avatarBox, singlePlayerButtonBox, vCoinBox);

        avatarBox.setAlignment(Pos.CENTER);
        singlePlayerButtonBox.setAlignment(Pos.CENTER);
        coinBox1.setAlignment(Pos.CENTER);
        coinBox2.setAlignment(Pos.CENTER);
        vCoinBox.setAlignment(Pos.CENTER);
        singlePlayerBox.setAlignment(Pos.CENTER);

        //button actions
        endTurnButton.setOnAction(event -> {
            game.removeCoins(numberOfCoins);
            game.aiTurn();
            resetNumberOfCoins();
        });

        //coin actions
        coinOneView.setOnMouseClicked(event -> {
            if(checkTurnStatus()) {
                coinOneView.setVisible(false);
                increaseNumberOfCoins();
                coins.setText(""+numberOfCoins);
            } else {

            }
        });


        singlePlayerScene = new Scene(singlePlayerBox, 800, 400);



        //Two Player Scene----------------------------------------------------------------------------------------------


        //Menu Scene----------------------------------------------------------------------------------------------------

        //the different components of the welcome screen
        Label welcomeLabel = new Label("Welcome to Nim");
        Button onePlayerButton = new Button("One Player");
        Button twoPlayerButton = new Button("Two Players");
        Pane welcomePane = new Pane();
        Image cornerImage = new Image("/images/coins_edited.jpg", true);
        ImageView cImageView = new ImageView(cornerImage);


        //setting the location of the components
        HBox buttonBox = new HBox(50, onePlayerButton, twoPlayerButton);
        VBox container = new VBox(100, welcomeLabel, buttonBox);
        buttonBox.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);


        //adding actions to components
        onePlayerButton.setOnAction(e -> primaryStage.setScene(singlePlayerScene));

        Image corneImage = new Image("/images/coins_edited.jpg");

        Scene welcomeScene = new Scene(container, 400, 200);
        primaryStage.setScene(welcomeScene);

        primaryStage.show();
    }

    private static boolean checkTurnStatus() {
        if(numberOfCoins<4) return true;
        else return false;
    }
    private static void increaseNumberOfCoins() {
        ++numberOfCoins;
    }

    private static void resetNumberOfCoins() {
        numberOfCoins = 0;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
