import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NimApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Nim");

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

        Image corneImage = new Image("/images/coins_edited.jpg");

        Scene welcomeScene = new Scene(container, 400, 200);
        primaryStage.setScene(welcomeScene);


        //One Player Scene
        Image userImage = new Image("/images/user_image.png");
        Image aiImage = new Image("/images/ai_image.jpg");

        


        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
