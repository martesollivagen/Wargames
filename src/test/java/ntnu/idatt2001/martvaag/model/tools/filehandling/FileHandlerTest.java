package ntnu.idatt2001.martvaag.model.tools.filehandling;

import ntnu.idatt2001.martvaag.model.battle.Army;
import ntnu.idatt2001.martvaag.model.tools.factory.UnitFactory;
import ntnu.idatt2001.martvaag.model.tools.enums.UnitTypes;
import ntnu.idatt2001.martvaag.model.unit.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for FileHandler class
 * @version 2022-05-19
 * @author martvaag
 */
public class FileHandlerTest {

    public ArrayList<Unit> addUnits(){
        ArrayList<Unit> units = new ArrayList<>();

        units.addAll(UnitFactory.createMultipleUnits(10, UnitTypes.INFANTRYUNIT,"Footman",100));
        units.addAll(UnitFactory.createMultipleUnits(5,UnitTypes.CAVALRYUNIT,"Knight",100));
        units.addAll(UnitFactory.createMultipleUnits(2, UnitTypes.RANGEDUNIT,"Archer",100));
        units.addAll(UnitFactory.createMultipleUnits(1,UnitTypes.COMMANDERUNIT,"Mountain King",100));

        return units;
    }

    @Test
    @DisplayName("Write an army to a file")
    public void writeArmyToFile(){
        String filePath = "src/test/java/ntnu/idatt2001/martvaag/model/tools/filehandling/humanArmyTestFile.csv";
        Army humanArmy = new Army("Human army", addUnits());
        File file = new File(filePath);
        FileHandler.writeToFile(file, humanArmy);
        assertTrue(file.exists());
        assertTrue(file.canWrite());
        assertTrue(file.length() > 0);
    }

    @Test
    @DisplayName("Read an army from a file")
    public void readAndCreateArmyFromFile() {
        String filePath = "src/main/resources/ntnu/idatt2001/martvaag/preCreatedArmy/human-army.csv";
        File file = new File(filePath);
        FileHandler.readArmyFromFile(filePath);
        assertTrue(file.exists());
        assertTrue(file.canRead());
        assertTrue(FileHandler.readArmyFromFile(filePath) instanceof Army);
    }
}