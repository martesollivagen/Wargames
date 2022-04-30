package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    public ArrayList<Unit> addUnits(){
        Unit unit11 = new InfantryUnit("Footman", 100);
        Unit unit12 = new CavalryUnit("Knight", 100);
        Unit unit13 = new RangedUnit("Archer", 100);
        Unit unit14 = new CommanderUnit("Mountain King", 180);

        ArrayList<Unit> units = new ArrayList<>();

        for(int i = 0; i<10; i++){
            units.add(unit11);
        }for(int i = 0; i<5; i++){
            units.add(unit12);
        }for(int i = 0; i<2; i++){
            units.add(unit13);
        }for(int i = 0; i<1; i++){
            units.add(unit14);
        }
        return units;
    }

    @Test
    @DisplayName("Write an army to a file")
    void writeToFile(){
        Army humanArmy = new Army("Human army", addUnits());
        File file = new File("src/main/resources/human-army.csv");
        FileHandler.writeToFile(file, humanArmy);
        assertTrue(file.exists());
        assertTrue(file.canWrite());
        assertTrue(file.length() > 0);
    }

    @Test
    @DisplayName("Read an army from a file")
    void readFile() {
        String filePath = "src/main/resources/ntnu/idatt2001/martvaag/PreCreatedArmy/human-army.csv";
        File file = new File(filePath);
        FileHandler.readArmyFromFile(filePath);
        assertTrue(file.exists());
        assertTrue(file.canRead());
    }

}