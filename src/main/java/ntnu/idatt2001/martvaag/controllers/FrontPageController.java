package ntnu.idatt2001.martvaag.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * controller for the front page of the application
 * @version 1.0 2022-05-18
 * @author martvaag
 */
public class FrontPageController {

    /**
     * opens the next page of the application
     * @param event event
     * @throws IOException IOException
     */
    public void createAnArmy(ActionEvent event) throws IOException {
        String createArmyFilePath = "/ntnu/idatt2001/martvaag/fxmlFiles/CreateArmy.fxml";
        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(createArmyFilePath)));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
