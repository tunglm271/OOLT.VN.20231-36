package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class CoGanhApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UIGame.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Game Cờ Gánh");
            stage.setScene(scene);
            stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
