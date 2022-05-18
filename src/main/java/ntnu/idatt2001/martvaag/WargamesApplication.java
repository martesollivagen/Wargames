package ntnu.idatt2001.martvaag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * main class that extends Application
 * used to start and open the application
 * @version 2022-05-18
 * @author martvaag
 */
public class WargamesApplication extends Application {
    /**
     * method to start the application which opens the front page
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
     * @param args Srgs
     */
    public static void main(String[] args) {
        launch(args);
    }
}
