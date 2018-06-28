import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NimApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Nim");

        Label welcomeLabel = new Label("Welcome to Nim");
        Scene welcomeScene = new Scene(welcomeLabel, 400, 200);
        primaryStage.setScene(welcomeScene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
