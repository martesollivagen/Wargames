package ntnu.idatt2001.martvaag.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * controller for the front page of the application
 *
 * @version 2022-05-23
 * @author martvaag
 */
public class FrontPageController {

    /**
     * opens the next page (main page) of the application
     * @param event event
     * @throws IOException IOException
     */
    public void createAnArmy(ActionEvent event) throws IOException {
        String createArmyFilePath = "/ntnu/idatt2001/martvaag/fxmlFiles/MainPage.fxml";
        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(createArmyFilePath)));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * empties the self created army file when application opens
     */
    public void initialize() {
        String filePathSelfCreatedArmy = "src/main/resources/ntnu/idatt2001/martvaag/selfCreatedArmy/SelfCreatedArmy.csv";
        try (FileWriter fileWriter = new FileWriter(filePathSelfCreatedArmy)) {
            fileWriter.write("Your army");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
