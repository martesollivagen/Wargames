package ntnu.idatt2001.martvaag.model.tools.observer;

import ntnu.idatt2001.martvaag.model.battle.Army;
import ntnu.idatt2001.martvaag.model.unit.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * class to give an observer notice when something changes in an observable object
 * @version 2022-05-22
 * @author martvaag
 */
public class Subject {

    /**
     * list of all observes to notice
     */
    List<Observer> observers;

    /**
     * constructor with list of all observers
     */
    public Subject(){
        observers = new ArrayList<>();
    }

    /**
     * method to add an observer to list of observers
     * @param observer observer
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * method to remove an observer from the list of observers
     * @param observer observer
     */
    public void detach(Observer observer){
        observers.remove(observer);
    }

    /**
     * method to notify the observers on a change that has happened
     * @param army army
     * @param unit unit
     */
    public void notifyObservers(Army army, Unit unit){
        observers.forEach(observer -> observer.update(army, unit));
    }
}