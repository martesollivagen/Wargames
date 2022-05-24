package ntnu.idatt2001.martvaag.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import ntnu.idatt2001.martvaag.model.animation.Animation;
import ntnu.idatt2001.martvaag.model.battle.Army;
import ntnu.idatt2001.martvaag.model.tools.filehandling.FileHandler;
import ntnu.idatt2001.martvaag.model.tools.enums.Terrain;
import ntnu.idatt2001.martvaag.model.tools.factory.UnitFactory;
import ntnu.idatt2001.martvaag.model.tools.enums.UnitTypes;
import ntnu.idatt2001.martvaag.model.unit.Unit;

import java.io.*;
import java.util.*;

/**
 * controller for the main page of the application
 *
 * @version 2022-05-23
 * @author martvaag
 */
public class MainPageController{

    //string paths for instructions and the different army files
    private final static String filePathInstructions = "src/main/resources/ntnu/idatt2001/martvaag/instructions/instructions.txt";
    private final static String filePathUnitTypeInfo = "src/main/resources/ntnu/idatt2001/martvaag/instructions/unitTypeInformation.txt";
    private final static String filePathHumanArmy = "src/main/resources/ntnu/idatt2001/martvaag/preCreatedArmy/human-army.csv";
    private final static String filePathOrcishHorde = "src/main/resources/ntnu/idatt2001/martvaag/preCreatedArmy/orcish-horde-army.csv";
    private final static String filePathStarWarsArmy = "src/main/resources/ntnu/idatt2001/martvaag/preCreatedArmy/Star-Wars-army.csv";
    private final static String filePathGameOfThrones = "src/main/resources/ntnu/idatt2001/martvaag/preCreatedArmy/gameOfThrones.csv";
    private final static String filePathSelfCreatedArmy = "src/main/resources/ntnu/idatt2001/martvaag/selfCreatedArmy/SelfCreatedArmy.csv";

    //images for each type of army
    private final Image orcishHordeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ntnu/idatt2001/martvaag/pictures/orcishHordeImage.jpeg")));
    private final Image humanArmyImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ntnu/idatt2001/martvaag/pictures/humanArmy.png")));
    private final Image starwarsImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ntnu/idatt2001/martvaag/pictures/starwarsImage.jpeg")));
    private final Image neutralArmyOneImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ntnu/idatt2001/martvaag/pictures/neutralArmy1.jpeg")));
    private final Image neutralArmyTwoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ntnu/idatt2001/martvaag/pictures/neutralArmy2.jpeg")));
    private final Image gameOfThronesImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ntnu/idatt2001/martvaag/pictures/gameOfThrones.png")));

    //armies to use in the simulation
    private Army armyOne, armyTwo;

    //file paths for the chosen armies
    private static String filePathArmyOne, filePathArmyTwo;

    //lists for unit type and terrain to set to a choice box
    private final UnitTypes[] unitTypes = {UnitTypes.INFANTRYUNIT, UnitTypes.RANGEDUNIT, UnitTypes.CAVALRYUNIT, UnitTypes.COMMANDERUNIT, UnitTypes.SUPPORTUNIT};
    private final Terrain[] terrains = {Terrain.PLAINS, Terrain.HILL, Terrain.FOREST};

    //confirmation / error message texts
    @FXML Text confirmationTextFileArmyOne;
    @FXML Text confirmationTextFileArmyTwo;
    @FXML Text confirmationTextSimulateBattle;
    @FXML Text confirmationTextCreateOwnArmy;
    @FXML Text confirmationTextResetArmies;

    //army and battle information
    @FXML TextArea armyOneInfo;
    @FXML TextArea armyOneUnits;
    @FXML TextArea armyTwoInfo;
    @FXML TextArea armyTwoUnits;
    @FXML TextArea battleResult;

    //create own army components
    @FXML ChoiceBox<UnitTypes> unitType;
    @FXML TextField nameOfUnit;
    @FXML TextField numberOfUnitsToAdd;
    @FXML TextField unitHealthInputField;

    //image views
    @FXML ImageView imageArmyOne;
    @FXML ImageView imageArmyTwo;
    @FXML ImageView winnerImage;

    //buttons
    @FXML Button simulateBattleButton;
    @FXML Button resetArmiesButton;
    @FXML Button instructionsButton;
    @FXML MenuButton downloadArmyButton;
    @FXML MenuButton chooseArmyOneButton;
    @FXML MenuButton chooseArmyTwoButton;

    //others
    @FXML Label vsLabel;
    @FXML ChoiceBox<Terrain> terrainChoiceBox;
    @FXML AnchorPane createOwnArmy;
    @FXML AnchorPane downloadOrChoose;
    @FXML Text viewUnitsArmyOne;
    @FXML Text viewUnitsArmyTwo;

    /**
     * method containing things that happens when this page is opened
     * adds values og unit types and terrain types to choice boxes
     */
    public void initialize(){
        unitType.getItems().addAll(unitTypes);
        terrainChoiceBox.getItems().addAll(terrains);
    }

    /**
     * ends the running code
     * close the application
     */
    public void closeApplication(){
        System.exit(0);
        Platform.exit();
    }

    /**
     * download an army for army one
     *
     * opens your files and let you choose
     * the file has to be a csv file with the right format in order to be used
     * sets armyOne and filePathArmyOne and displays the army and filepath
     * if something goes wrong it sets a message to the error text for the army
     */
    public void downloadArmy1(){

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Files", "*.csv"));
            File file = fileChooser.showOpenDialog(null);

            if (file != null){
                armyOne = FileHandler.readArmyFromFile(file.getPath());
                filePathArmyOne = file.getPath();

                armyOneUnits.setText(FileHandler.readArmyFromFile(file.getPath()).toString());
                confirmationTextFileArmyOne.setFill(Paint.valueOf("#0000CD"));
                confirmationTextFileArmyOne.setText("Selected file for Army 1: " + file.getName());
                setArmyOneInfo();
                setViewUnitTextAndImage(viewUnitsArmyOne, imageArmyOne, neutralArmyOneImage);
            }
        } catch (NullPointerException e){
            noFileSelected(confirmationTextFileArmyOne);
        } catch (IllegalArgumentException i){
            invalidFileFormat(confirmationTextFileArmyOne);
        }
    }

    /**
     * download an army for army two
     *
     * opens your files and let you choose
     * the file has to be a csv file with the right format in order to be used
     * sets armyTwo and filePathArmyTwo and displays the army and filepath
     * if something goes wrong it sets a message to the error text for the army
     */
    public void downloadArmy2(){

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Files", "*.csv"));
            File file = fileChooser.showOpenDialog(null);

            if (file != null){
                armyTwo = FileHandler.readArmyFromFile(file.getPath());
                filePathArmyTwo = file.getPath();

                armyTwoUnits.setText(FileHandler.readArmyFromFile(file.getPath()).toString());
                confirmationTextFileArmyTwo.setFill(Paint.valueOf("#0000CD"));
                confirmationTextFileArmyTwo.setText("Selected file for Army 2: " + file.getName());
                setArmyTwoInfo();
                setViewUnitTextAndImage(viewUnitsArmyTwo, imageArmyTwo, neutralArmyTwoImage);
            }
        } catch (NullPointerException e){
            noFileSelected(confirmationTextFileArmyTwo);
        } catch (IllegalArgumentException i){
            invalidFileFormat(confirmationTextFileArmyTwo);
        }
    }

    /**
     * sets error message if no file is selected
     * @param confirmationText confirmation text
     */
    public void noFileSelected(Text confirmationText){
        confirmationText.setFill(Paint.valueOf("#8B0000"));
        confirmationText.setText("No file was selected");
    }

    /**
     * sets error message for invalid file format
     * @param confirmationText confirmation text
     */
    public void invalidFileFormat(Text confirmationText){
        confirmationText.setFill(Paint.valueOf("#8B0000"));
        confirmationText.setText("This is an invalid file format, you have to use a csv file with the right format");
    }

    /**
     * sets text and image visible
     *
     * @param viewUnits message to view units
     * @param imageView image view
     * @param image     image
     */
    public void setViewUnitTextAndImage(Text viewUnits, ImageView imageView, Image image){
        viewUnits.setVisible(true);
        imageView.setImage(image);
    }

    /**
     * information about the number of units
     *
     * @param totalUnits     total number of units
     * @param infantryUnits  number of infantry units
     * @param rangedUnits    number of ranged units
     * @param cavalryUnits   number of cavalry units
     * @param commanderUnits number of commander units
     * @param supportUnits   number of support units
     * @return army info
     */
    public String armyInfo(Integer totalUnits, Integer infantryUnits, Integer rangedUnits, Integer cavalryUnits, Integer commanderUnits, Integer supportUnits){
        return "\n\nTotal number of units:        " + totalUnits +
                "\nTotal number of InfantryUnits:  " + infantryUnits +
                "\nTotal number of RangedUnits:    " + rangedUnits +
                "\nTotal number of CavalryUnits:   " + cavalryUnits +
                "\nTotal number of CommanderUnits: " + commanderUnits +
                "\nTotal number of Support units: " + supportUnits;
    }

    /**
     * check if an army does not have any units left
     * @return army info with no units
     */
    public String emptyArmy(){
        return armyInfo(0,0,0,0,0,0);
    }

    /**
     * set army info for army one
     */
    public void setArmyOneInfo(){
        armyOneInfo.setText("Army : " + armyOne.getName() +
                armyInfo(armyOne.getUnits().size(), armyOne.getInfantryUnits().size(), armyOne.getRangedUnits().size(), armyOne.getCavalryUnits().size(), armyOne.getCommanderUnits().size(), armyOne.getSupportUnits().size()));
    }

    /**
     * set army info for army two
     */
    public void setArmyTwoInfo(){
        armyTwoInfo.setText("Army : " + armyTwo.getName() +
                armyInfo(armyTwo.getUnits().size(), armyTwo.getInfantryUnits().size(), armyTwo.getRangedUnits().size(), armyTwo.getCavalryUnits().size(), armyTwo.getCommanderUnits().size(), armyTwo.getSupportUnits().size()));
    }

    /**
     * disable all fields during the animation
     */
    public void disableAllFields(){
        resetArmiesButton.setDisable(true);
        simulateBattleButton.setDisable(true);
        terrainChoiceBox.setDisable(true);
        createOwnArmy.setDisable(true);
        downloadOrChoose.setDisable(true);
        viewUnitsArmyOne.setVisible(false);
        viewUnitsArmyTwo.setVisible(false);
    }

    /**
     * enables all fields after reset
     */
    public void enableAllFields(){
        winnerImage.setVisible(false);
        imageArmyOne.setVisible(true);
        imageArmyTwo.setVisible(true);
        battleResult.setText("");
        simulateBattleButton.setDisable(false);
        terrainChoiceBox.setDisable(false);
        createOwnArmy.setDisable(false);
        downloadOrChoose.setDisable(false);
    }

    /**
     * empty all error message texts
     */
    public void emptyAllErrorMessages(){
        confirmationTextResetArmies.setText("");
        confirmationTextCreateOwnArmy.setText("");
        confirmationTextSimulateBattle.setText("");
    }

    /**
     * removes the two images for each army and displays winner image
     * also un-disables the reset button and sets "view units" text visable
     */
    public void afterBattleEdits(){
        winnerImage.setVisible(true);
        imageArmyOne.setVisible(false);
        imageArmyTwo.setVisible(false);
        resetArmiesButton.setDisable(false);
        viewUnitsArmyOne.setVisible(true);
        viewUnitsArmyTwo.setVisible(true);
    }

    /**
     * simulate method
     * first evaluates the armies and terrain
     * then creates an animation and later displays winner and the units left in the two armies
     */
    public void fight() {
        try {
            Terrain terrain = terrainChoiceBox.getValue();
            Animation animation = new Animation(armyOne, armyTwo, terrain);

            disableAllFields();
            emptyAllErrorMessages();

            Runnable setWinner = () -> {
                battleResult.setText(animation.getBattle().toString());
                if (animation.getBattle().getWinner().equals(armyOne)) {
                    winnerImage.setImage(imageArmyOne.getImage());
                } else {
                    winnerImage.setImage(imageArmyTwo.getImage());
                }
                afterBattleEdits();
                armyOneUnits.setText(animation.getBattle().armyOne().toString());
                armyTwoUnits.setText(animation.getBattle().armyTwo().toString());
            };

            Runnable task = () -> {
                try {
                    animation.createAnimation(armyOneInfo, armyTwoInfo);
                    Platform.runLater(setWinner);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(task).start();
        } catch (NullPointerException | IllegalArgumentException n){
            confirmationTextSimulateBattle.setText(n.getMessage());
        }
    }

    /**
     * reset the armies after a simulation
     */
    public void resetArmies(){
        try {
            if (!(armyOneInfo.getText().contains(emptyArmy()) || armyTwoInfo.getText().contains(emptyArmy()))) {
                confirmationTextResetArmies.setText("You have to do a simulation before you can reset");
            } else {
                armyOne = FileHandler.readArmyFromFile(filePathArmyOne);
                armyTwo = FileHandler.readArmyFromFile(filePathArmyTwo);

                armyOneUnits.setText(FileHandler.readArmyFromFile(filePathArmyOne).toString());
                armyTwoUnits.setText(FileHandler.readArmyFromFile(filePathArmyTwo).toString());

                setArmyOneInfo();
                setArmyTwoInfo();

                enableAllFields();
            }
        } catch (NullPointerException n) {
            confirmationTextResetArmies.setText("You have to set two armies and run a simulation first");
        }
    }

    /**
     * set image and army info for pre-made armies
     *
     * @param imageView        image view
     * @param image            image
     * @param armyInfo         army info
     * @param filePath         pile path
     * @param viewUnits        message to view units
     * @param confirmationText confirmation text
     */
    public void setArmy(ImageView imageView, Image image, TextArea armyInfo, String filePath, Text viewUnits, Text confirmationText){
        imageView.setImage(image);
        armyInfo.setText(FileHandler.readArmyFromFile(filePath).toString());
        viewUnits.setVisible(true);
        confirmationText.setText("");
    }

    /**
     * set human army for army one
     */
    public void humanArmy1(){
        setArmy(imageArmyOne,humanArmyImage, armyOneUnits,filePathHumanArmy,viewUnitsArmyOne, confirmationTextFileArmyOne);
        armyOne = FileHandler.readArmyFromFile(filePathHumanArmy);
        filePathArmyOne = filePathHumanArmy;
        setArmyOneInfo();
    }

    /**
     * set orcish horde army for army one
     */
    public void orcishHorde1(){
        setArmy(imageArmyOne, orcishHordeImage, armyOneUnits, filePathOrcishHorde,viewUnitsArmyOne, confirmationTextFileArmyOne);
        armyOne = FileHandler.readArmyFromFile(filePathOrcishHorde);
        filePathArmyOne = filePathOrcishHorde;
        setArmyOneInfo();
    }

    /**
     * set game of thrones army for army one
     */
    public void gameOfThrones1(){
        setArmy(imageArmyOne,gameOfThronesImage, armyOneUnits,filePathGameOfThrones,viewUnitsArmyOne, confirmationTextFileArmyOne);
        armyOne = FileHandler.readArmyFromFile(filePathGameOfThrones);
        filePathArmyOne = filePathGameOfThrones;
        setArmyOneInfo();
    }

    /**
     * set star wars army for army two
     */
    public void starWarsArmy1(){
        setArmy(imageArmyOne,starwarsImage, armyOneUnits,filePathStarWarsArmy,viewUnitsArmyOne, confirmationTextFileArmyOne);
        armyOne = FileHandler.readArmyFromFile(filePathStarWarsArmy);
        filePathArmyOne = filePathStarWarsArmy;
        setArmyOneInfo();
    }

    /**
     * set human army for army two
     */
    public void humanArmy2(){
        setArmy(imageArmyTwo,humanArmyImage, armyTwoUnits,filePathHumanArmy,viewUnitsArmyTwo, confirmationTextFileArmyTwo);
        armyTwo = FileHandler.readArmyFromFile(filePathHumanArmy);
        filePathArmyTwo = filePathHumanArmy;
        setArmyTwoInfo();
    }

    /**
     * set orcish horde army for army two
     */
    public void orcishHorde2(){
        setArmy(imageArmyTwo,orcishHordeImage, armyTwoUnits,filePathOrcishHorde,viewUnitsArmyTwo, confirmationTextFileArmyTwo);
        armyTwo = FileHandler.readArmyFromFile(filePathOrcishHorde);
        filePathArmyTwo = filePathOrcishHorde;
        setArmyTwoInfo();
    }

    /**
     * set game of thrones army for army two
     */
    public void gameOfThrones2(){
        setArmy(imageArmyTwo,gameOfThronesImage, armyTwoUnits,filePathGameOfThrones,viewUnitsArmyTwo, confirmationTextFileArmyTwo);
        armyTwo = FileHandler.readArmyFromFile(filePathGameOfThrones);
        filePathArmyTwo = filePathGameOfThrones;
        setArmyTwoInfo();
    }

    /**
     * set star wars army for army two
     */
    public void starWarsArmy2(){
        setArmy(imageArmyTwo,starwarsImage, armyTwoUnits,filePathStarWarsArmy,viewUnitsArmyTwo, confirmationTextFileArmyTwo);
        armyTwo = FileHandler.readArmyFromFile(filePathStarWarsArmy);
        filePathArmyTwo = filePathStarWarsArmy;
        setArmyTwoInfo();
    }

    /**
     * create own army
     * uses the input fields and UnitFactory to create units
     * writes this new army to the selfCreatedArmy file
     */
    public void createArmyForArmyOne(){
        try {
            ArrayList<Unit> unitsToAdd = UnitFactory.createMultipleUnits(Integer.parseInt(numberOfUnitsToAdd.getText().trim()), unitType.getValue(), nameOfUnit.getText().trim(), Integer.parseInt(unitHealthInputField.getText().trim()));
            setViewUnitTextAndImage(viewUnitsArmyOne, imageArmyOne,neutralArmyOneImage);
            filePathArmyOne = filePathSelfCreatedArmy;
            armyOne = FileHandler.readArmyFromFile(filePathSelfCreatedArmy);
            if (armyOne.getUnits().size() >= 10000){
                confirmationTextCreateOwnArmy.setFill(Paint.valueOf("#8B0000"));
                confirmationTextCreateOwnArmy.setText("You cannot have over 10 000 units in your army");
            } else {
                armyOne.addAll(unitsToAdd);

                StringBuilder finalUnits = new StringBuilder();
                armyOne.getUnits().forEach(unit -> finalUnits.append("\n").append(unit.toString()));
                armyOneUnits.setText(finalUnits.toString());

                File fileArmyOne = new File(filePathSelfCreatedArmy);
                FileHandler.writeToFile(fileArmyOne, armyOne);

                confirmationTextCreateOwnArmy.setFill(Paint.valueOf("#0000CD"));
                confirmationTextCreateOwnArmy.setText("Your '" + nameOfUnit.getText().trim() + "' unit(s) where added to your army");
                setArmyOneInfo();
                clearFieldsCreateOwnArmy();
            }
        } catch (NumberFormatException e){
            confirmationTextCreateOwnArmy.setFill(Paint.valueOf("#8B0000"));
            confirmationTextCreateOwnArmy.setText("Health and number of units has to be numbers");
        } catch (IllegalArgumentException n){
            confirmationTextCreateOwnArmy.setFill(Paint.valueOf("#8B0000"));
            confirmationTextCreateOwnArmy.setText(n.getMessage());
        }
    }

    /**
     * empty the unit list for the self-made army to start over
     */
    public void resetSelfCreatedArmy(){
        try {
            if (!armyOne.getName().equals("Your army")){
                confirmationTextCreateOwnArmy.setFill(Paint.valueOf("#8B0000"));
                confirmationTextCreateOwnArmy.setText("The army you are trying to empty is not yours");
            } else if (!armyOne.hasUnits()){
                confirmationTextCreateOwnArmy.setFill(Paint.valueOf("#8B0000"));
                confirmationTextCreateOwnArmy.setText("Your army is already empty");
            } else {
                clearFieldsCreateOwnArmy();

                try (FileWriter fileWriter = new FileWriter(filePathSelfCreatedArmy)) {
                    fileWriter.write("Your army");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                armyOne = FileHandler.readArmyFromFile(filePathSelfCreatedArmy);
                setArmyOneInfo();
                armyOneUnits.setText(FileHandler.readArmyFromFile(filePathSelfCreatedArmy).toString());
                confirmationTextCreateOwnArmy.setFill(Paint.valueOf("#0000CD"));
                confirmationTextCreateOwnArmy.setText("Your army is now empty");
            }
        } catch (NullPointerException n) {
            confirmationTextCreateOwnArmy.setFill(Paint.valueOf("#8B0000"));
            confirmationTextCreateOwnArmy.setText("You have not created an army yet");
        }
    }

    /**
     * clear all fields and text used for creating own army
     */
    public void clearFieldsCreateOwnArmy(){
        unitType.getSelectionModel().clearSelection();
        numberOfUnitsToAdd.setText("");
        nameOfUnit.setText("");
        unitHealthInputField.setText("");
        confirmationTextFileArmyOne.setText("");
    }

    /**
     * show text field with all units for army one when computer mouse enters the text field
     */
    public void showUnitsArmyOne(){
        if (!armyOneUnits.getText().isEmpty() && !resetArmiesButton.isDisabled()){
            armyOneUnits.setVisible(true);
        }
    }

    /**
     * remove text field with all units for army one when computer mouse exits the text field
     */
    public void removeUnitsArmyOne(){
        armyOneUnits.setVisible(false);
    }

    /**
     * show text field with all units for army two when computer mouse enters the text field
     */
    public void showUnitsArmyTwo(){
        if (!armyTwoUnits.getText().isEmpty() && !resetArmiesButton.isDisabled()) {
            armyTwoUnits.setVisible(true);
        }
    }

    /**
     * remove text field with all units for army two when computer mouse exits the text field
     */
    public void removeUnitsArmyTwo(){
        armyTwoUnits.setVisible(false);
    }

    /**
     * alert window with simple instructions explaining how the application works
     * reads the instructions from a txt file
     */
    public void instructionAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(600,700);
        alert.setTitle("Instructions");
        alert.setHeaderText("Instruction manual for Wargames");
        alert.setContentText(FileHandler.readTextFromFile(filePathInstructions));

        ButtonType okButton = new ButtonType("OK");

        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
        alert.close();
    }

    /**
     * alert window with information about each unit type for when the user is going to make their own army
     * reads the information from a txt file
     */
    public void showUnitTypeInfo(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(400,600);
        alert.setTitle("Unit types");
        alert.setHeaderText("Information about the different unit types");
        alert.setContentText(FileHandler.readTextFromFile(filePathUnitTypeInfo));

        ButtonType okButton = new ButtonType("OK");

        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
        alert.close();
    }
}