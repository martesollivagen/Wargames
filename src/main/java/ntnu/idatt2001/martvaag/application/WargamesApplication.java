package ntnu.idatt2001.martvaag.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * main class that extends Application
 * used to open the front page of the application
 * @version 2022-05-22
 * @author martvaag
 */
public class WargamesApplication extends Application {

    /**
     * method to open the front page of the application
     * @param primaryStage primary stage
     * @throws Exception exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        String frontPageFilePath = "/ntnu/idatt2001/martvaag/fxmlFiles/FrontPage.fxml";
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(frontPageFilePath)));
        primaryStage.setTitle("Wargames");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * main-method
     * @param args Args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
