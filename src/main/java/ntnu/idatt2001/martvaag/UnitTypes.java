package ntnu.idatt2001.martvaag;

/**
 * enum for all the different valid unit types
 * with an attached String value of the unit type
 * @version 2022-05-10
 * @author martvaag
 */
public enum UnitTypes {

    INFANTRYUNIT("infantryunit"),
    CAVALRYUNIT("cavalryunit"),
    RANGEDUNIT("rangedunit"),
    COMMANDERUNIT("commanderunit");

    private final String unitType;

    UnitTypes(String unitName){
        this.unitType = unitName;
    }
}
