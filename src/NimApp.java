import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NimApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Nim");

        Label welcomeLabel = new Label("Welcome to Nim");
        Button onePlayerButton = new Button("One Player");
        Button twoPlayerButton = new Button("Two Players");

        HBox buttonBox = new HBox(50, onePlayerButton, twoPlayerButton);
        VBox container = new VBox(10, welcomeLabel, buttonBox);
        Scene welcomeScene = new Scene(container, 400, 200);
        primaryStage.setScene(welcomeScene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
