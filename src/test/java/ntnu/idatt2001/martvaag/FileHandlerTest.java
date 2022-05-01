package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.unit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for FileHandler class
 * @version 2022-05-01
 * @author martvaag
 */
class FileHandlerTest {

    public ArrayList<Unit> addUnits(){
        ArrayList<Unit> units = new ArrayList<>();

        units.addAll(UnitFactory.createMultipleUnits(10,"infantryunit","Footman",100));
        units.addAll(UnitFactory.createMultipleUnits(5,"cavalryunit","Knight",100));
        units.addAll(UnitFactory.createMultipleUnits(2,"rangedunit","Archer",100));
        units.addAll(UnitFactory.createMultipleUnits(1,"commanderunit","Mountain King",100));

        return units;
    }

    @Test
    @DisplayName("Write an army to a file")
    void writeArmyToFile(){
        Army humanArmy = new Army("Human army", addUnits());
        File file = new File("src/main/resources/human-army.csv");
        FileHandler.writeToFile(file, humanArmy);
        assertTrue(file.exists());
        assertTrue(file.canWrite());
        assertTrue(file.length() > 0);
    }

    @Test
    @DisplayName("Read an army from a file")
    void readArmyFromFile() {
        String filePath = "src/main/resources/ntnu/idatt2001/martvaag/PreCreatedArmy/human-army.csv";
        File file = new File(filePath);
        FileHandler.readArmyFromFile(filePath);
        assertTrue(file.exists());
        assertTrue(file.canRead());
    }

}