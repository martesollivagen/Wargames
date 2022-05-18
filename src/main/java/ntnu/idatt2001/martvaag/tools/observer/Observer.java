package ntnu.idatt2001.martvaag.tools.observer;

import ntnu.idatt2001.martvaag.battle.Army;
import ntnu.idatt2001.martvaag.unit.Unit;

/**
 * interface to use to get informed of changes that has happened in an observable objects
 * @version 1.0 2022-05-18
 * @author martvaag
 */
public interface Observer {

    /**
     * method for whenever an observed object is changed
     * @param army army
     * @param unit unit
     */
    void update(Army army, Unit unit);
}