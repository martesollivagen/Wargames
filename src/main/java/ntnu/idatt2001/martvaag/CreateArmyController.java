package ntnu.idatt2001.martvaag;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ntnu.idatt2001.martvaag.Unit.Unit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateArmyController implements Initializable {
    private final static String filePathHumanArmy = "src/main/resources/ntnu/idatt2001/martvaag/PreCreatedArmy/human-army.csv";
    private final static String filePathOrcishHorde = "src/main/resources/ntnu/idatt2001/martvaag/PreCreatedArmy/orcish-horde-army.csv";
    private final static String filePathDisneyArmy = "src/main/resources/ntnu/idatt2001/martvaag/PreCreatedArmy/Disney-army.csv";
    private final static String filePathStarWarsArmy = "src/main/resources/ntnu/idatt2001/martvaag/PreCreatedArmy/Star-Wars-army.csv";
    private final static String filePathTVSeriesArmy = "src/main/resources/ntnu/idatt2001/martvaag/PreCreatedArmy/TV-series.csv";

    Army army1, army2;
    private static String filePathArmy1, filePathArmy2;
    List<String> lstFile;

    @FXML Label confirmationLabelFile1;
    @FXML Label confirmationLabelFile2;
    @FXML Label conformationLabelBattle;
    @FXML TextArea army1Units;
    @FXML TextArea army2Units;
    @FXML TextArea battleResult;
    @FXML TextArea army1Info;
    @FXML TextArea army2Info;
    @FXML Button simulateButton;
    @FXML TextField addUnitArmy1;
    @FXML TextField addUnitArmy2;

    public static String getFilePathArmy1() {
        return filePathArmy1;
    }

    public static String getFilePathArmy2() {
        return filePathArmy2;
    }

    public void closeApplication(){
        Platform.exit();
    }

    public void openSimulateScene(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SimulateBattle.fxml")));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = new Stage();
        window.setTitle("Simulated battle");
        window.setScene(tableViewScene);
        window.initModality(Modality.WINDOW_MODAL);
        window.show();
    }

    public void downloadArmy1(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Files", lstFile));
        File file = fileChooser.showOpenDialog(null);

        if (file != null){
            confirmationLabelFile1.setText("Selected file for Army 1: " + file.getName());
        }
        assert file != null;
        army1Units.setText(Army.readArmyFromFile(file.getPath()).toString());
        army1 = Army.readArmyFromFile(file.getPath());
        setArmy1Info();
        Army.writeToFile(new File("src/main/resources/" + army1.getName().trim().replaceAll("\\W","") + "-copy.csv"), army1);
        filePathArmy1 = file.getPath();
    }

    public void downloadArmy2(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Files", lstFile));
        File file = fileChooser.showOpenDialog(null);

        if (file != null){
            confirmationLabelFile2.setText("Selected file for Army 2: " + file.getName());
        }
        assert file != null;
        army2Units.setText(Army.readArmyFromFile(file.getPath()).toString());
        army2 = Army.readArmyFromFile(file.getPath());
        setArmy2Info();
        Army.writeToFile(new File("src/main/resources/" + army2.getName().trim().replaceAll("\\W","") + "-copy.csv"), army2);
        filePathArmy2 = file.getPath();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        lstFile = new ArrayList<>();
        lstFile.add("*.doc");
        lstFile.add("*.pdf");
        lstFile.add("*.csv");
    }

    public void fight(){
        if (army1Units.getText().isBlank() || army2Units.getText().isBlank()){
            conformationLabelBattle.setText("Army 1 and army 2 cannot be empty");
        } else {
            Battle battle = new Battle(army1, army2);
            String winner = battle.simulate().toString();
            army1Units.setText(army1.toString());
            army2Units.setText(army2.toString());
            battleResult.setText("Winner of this battle is: " + winner);

            setArmy1Info();
            setArmy2Info();

            conformationLabelBattle.setText("");
        }
    }

    public void resetArmy(){
        army1 = Army.readArmyFromFile(filePathArmy1);
        army2 = Army.readArmyFromFile(filePathArmy2);

        army1Units.setText(Army.readArmyFromFile(filePathArmy1).toString());
        army2Units.setText(Army.readArmyFromFile(filePathArmy2).toString());

        setArmy1Info();
        setArmy2Info();
    }

    public void setArmy1Info(){
        army1Info.setText("Army 1: " + army1.getName() +
                "\n\nTotal number of units:        " + army1.getUnits().size() +
                "\nTotal number of InfantryUnits:  " + army1.getInfantryUnits().size() +
                "\nTotal number of RangedUnits:    " + army1.getRangedUnits().size() +
                "\nTotal number of CavalryUnits:   " + army1.getCavalryUnits().size() +
                "\nTotal number of CommanderUnits: " + army1.getCommanderUnits().size());
    }

    public void setArmy2Info(){
        army2Info.setText("Army 2: " + army2.getName() +
                "\n\nTotal number of units:        " + army2.getUnits().size() +
                "\nTotal number of InfantryUnits:  " + army2.getInfantryUnits().size() +
                "\nTotal number of RangedUnits:    " + army2.getRangedUnits().size() +
                "\nTotal number of CavalryUnits:   " + army2.getCavalryUnits().size() +
                "\nTotal number of CommanderUnits: " + army2.getCommanderUnits().size());
    }

    public void backToFrontPage(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FrontPage.fxml")));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void humanArmy1(){
        army1Units.setText(Army.readArmyFromFile(filePathHumanArmy).toString());
        army1 = Army.readArmyFromFile(filePathHumanArmy);
        setArmy1Info();
        filePathArmy1 = filePathHumanArmy;
    }

    public void orcishHorde1(){
        army1Units.setText(Army.readArmyFromFile(filePathOrcishHorde).toString());
        army1 = Army.readArmyFromFile(filePathOrcishHorde);
        setArmy1Info();
        filePathArmy1 = filePathOrcishHorde;
    }

    public void disneyArmy1(){
        army1Units.setText(Army.readArmyFromFile(filePathDisneyArmy).toString());
        army1 = Army.readArmyFromFile(filePathDisneyArmy);
        setArmy1Info();
        filePathArmy1 = filePathDisneyArmy;
    }

    public void starWarsArmy1(){
        army1Units.setText(Army.readArmyFromFile(filePathStarWarsArmy).toString());
        army1 = Army.readArmyFromFile(filePathStarWarsArmy);
        setArmy1Info();
        filePathArmy1 = filePathStarWarsArmy;
    }

    public void tvSeriesArmy1(){
        army1Units.setText(Army.readArmyFromFile(filePathTVSeriesArmy).toString());
        army1 = Army.readArmyFromFile(filePathTVSeriesArmy);
        setArmy1Info();
        filePathArmy1 = filePathTVSeriesArmy;
    }

    public void humanArmy2(){
        army2Units.setText(Army.readArmyFromFile(filePathHumanArmy).toString());
        army2 = Army.readArmyFromFile(filePathHumanArmy);
        setArmy2Info();
        filePathArmy2 = filePathHumanArmy;
    }

    public void orcishHorde2(){
        army2Units.setText(Army.readArmyFromFile(filePathOrcishHorde).toString());
        army2 = Army.readArmyFromFile(filePathOrcishHorde);
        setArmy2Info();
        filePathArmy2 = filePathOrcishHorde;
    }

    public void disneyArmy2(){
        army2Units.setText(Army.readArmyFromFile(filePathDisneyArmy).toString());
        army2 = Army.readArmyFromFile(filePathDisneyArmy);
        setArmy2Info();
        filePathArmy2 = filePathDisneyArmy;
    }

    public void starWarsArmy2(){
        army2Units.setText(Army.readArmyFromFile(filePathStarWarsArmy).toString());
        army2 = Army.readArmyFromFile(filePathStarWarsArmy);
        setArmy2Info();
        filePathArmy2 = filePathStarWarsArmy;
    }

    public void tvSeriesArmy2(){
        army2Units.setText(Army.readArmyFromFile(filePathTVSeriesArmy).toString());
        army2 = Army.readArmyFromFile(filePathTVSeriesArmy);
        setArmy2Info();
        filePathArmy2 = filePathTVSeriesArmy;
    }

    public void createArmy1(){
        File fileArmy1 = new File("src/main/resources/ntnu/idatt2001/martvaag/Army1.csv");

        String [] list = addUnitArmy1.getText().split(",");
        Unit addedUnit = UnitFactory.createUnit(list[0].trim(), list[1].trim(), Integer.parseInt(list[2].trim()));

        army1 = Army.readArmyFromFile("src/main/resources/ntnu/idatt2001/martvaag/Army1.csv");
        army1.add(addedUnit);

        String s = "";
        for (Unit unit : army1.getUnits()){
            s += unit.toString() + "\n";
            army1Units.setText(s);
            Army.writeToFile(fileArmy1, army1);
        }
        setArmy1Info();
        filePathArmy1 = "src/main/resources/ntnu/idatt2001/martvaag/Army1.csv";
    }

    public void createArmy2(){
        File fileArmy1 = new File("src/main/resources/ntnu/idatt2001/martvaag/Army2.csv");

        String [] list = addUnitArmy2.getText().split(",");
        Unit addedUnit = UnitFactory.createUnit(list[0].trim(), list[1].trim(), Integer.parseInt(list[2].trim()));

        army2 = Army.readArmyFromFile("src/main/resources/ntnu/idatt2001/martvaag/Army2.csv");
        army2.add(addedUnit);

        String s = "";
        for (Unit unit : army2.getUnits()){
            s += unit.toString() + "\n";
            army2Units.setText(s);
            Army.writeToFile(fileArmy1, army2);
        }
        setArmy2Info();
        filePathArmy2 = "src/main/resources/ntnu/idatt2001/martvaag/Army2.csv";
    }
}
