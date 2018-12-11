package pl.rynbou.weather.window;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.rynbou.weather.Datetime;
import pl.rynbou.weather.Reading;

import java.io.FileInputStream;

//TODO: Rewrite
public class MainWindow extends Application {
    @FXML
    Button button;
    @FXML
    Label label0, label1, label2;

    @FXML
    private void initialize() {
        update();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        String path = "fxmls/MainWindow.fxml";
        FileInputStream stream = new FileInputStream(path);
        Pane root = loader.load(stream);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Weather client");
        stage.show();
    }

    public void update() {
        Datetime t = new Datetime();

        Reading.reloadReadings();
        Reading downstairs = Reading.closestReading(Reading.Location.DOWNSTAIRS, t.getDate("UTC"));
        Reading outside = Reading.closestReading(Reading.Location.OUTSIDE, t.getDate("UTC"));
        Reading upstairs = Reading.closestReading(Reading.Location.UPSTAIRS, t.getDate("UTC"));

        label0.setText(
                "OUTSIDE\n" +
                        "T: " + outside.getTemperature() + "°C\n" +
                        "P: " + upstairs.getPressure() + "\n" +
                        outside.getTimestamp());

        label1.setText(
                "UPSTAIRS\n" +
                        "T: " + upstairs.getTemperature() + "°C\n" +
                        "H: " + upstairs.getHumidity() + "%\n" +
                        upstairs.getTimestamp());

        label2.setText(
                "DOWNSTAIRS\n" +
                        "T: " + downstairs.getTemperature() + "°C\n" +
                        "H: " + downstairs.getHumidity() + "%\n" +
                        downstairs.getTimestamp());
    }

    public static void launch() {
        Application.launch();
    }
}
