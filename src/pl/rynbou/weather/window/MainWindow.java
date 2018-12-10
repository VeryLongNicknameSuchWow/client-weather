package pl.rynbou.weather.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        String path = "fxmls/MainWindow.fxml";
        FileInputStream stream = new FileInputStream(path);
        VBox root = (VBox) loader.load(stream);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("JD");
        stage.show();
    }

    public static void launch() {
        Application.launch();
    }
}
