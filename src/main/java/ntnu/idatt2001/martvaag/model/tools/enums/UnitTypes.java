package ntnu.idatt2001.martvaag.model.tools.enums;

/**
 * enum for all the different valid unit types
 * with an attached String value of the unit type
 * @version 2022-05-18
 * @author martvaag
 */
public enum UnitTypes {

    INFANTRYUNIT("infantryunit"),
    CAVALRYUNIT("cavalryunit"),
    RANGEDUNIT("rangedunit"),
    COMMANDERUNIT("commanderunit"),
    SUPPORTUNIT("supportunit");

    UnitTypes(String unitName){
    }
}