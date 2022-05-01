package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.unit.Unit;

import java.io.*;
import java.util.ArrayList;

/**
 * class for fileHandling, includes methods for writing and reading an army from a file
 * @version 2022-04-030
 * @author martvaag
 */
public class FileHandler {

    /**
     * write an army to a file
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
                army.add(UnitFactory.createUnit(list[0], list[1], Integer.parseInt(list[2])));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return army;
    }
}
