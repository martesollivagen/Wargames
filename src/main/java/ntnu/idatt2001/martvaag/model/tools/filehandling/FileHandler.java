package ntnu.idatt2001.martvaag.model.tools.filehandling;

import ntnu.idatt2001.martvaag.model.battle.Army;
import ntnu.idatt2001.martvaag.model.tools.factory.UnitFactory;
import ntnu.idatt2001.martvaag.model.tools.enums.UnitTypes;
import ntnu.idatt2001.martvaag.model.unit.Unit;

import java.io.*;
import java.util.ArrayList;

/**
 * class for fileHandling
 * includes methods for writing and reading an army from a file and reading a text from a file
 *
 * @version 2022-05-23
 * @author martvaag
 */
public class FileHandler {

    /**
     * write an army to a file
     * writes every unit's type, name and health-value, separated by comma on their own line
     *
     * @param file file
     * @param army army
     */
    public static void writeToFile(File file, Army army){

        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(army.getName());
            for (Unit unit : army.getUnits()){
                fileWriter.write("\n" + unit.toString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * read an Army from a file
     * reads army's name from the first line, and every unit's type, name and health-value, separated by comma, and turns it into an army
     *
     * @param path file path
     * @return Army from file
     */
    public static Army readArmyFromFile(String path){
        Army army = null;
        ArrayList<Unit> units = new ArrayList<>();
        File fileToRead = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))){
            String line = reader.readLine();
            army = new Army(line, units);
            while ((line = reader.readLine()) != null){
                String [] list = line.split(",");
                army.add(UnitFactory.createUnit(UnitTypes.valueOf(list[0].trim().toUpperCase()), list[1], Integer.parseInt(list[2])));
            }
        } catch (IOException | NullPointerException e){
            e.printStackTrace();
        }
        return army;
    }

    /**
     * reads text from a file
     * @return text
     */
    public static String readTextFromFile(String filepath){
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            reader.lines().forEach(line -> text.append("\n").append(line));
        } catch (IOException | NullPointerException e){
            e.printStackTrace();
        }
        return text.toString();
    }
}
