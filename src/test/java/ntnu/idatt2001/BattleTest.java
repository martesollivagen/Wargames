package ntnu.idatt2001;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    void simulate() {
        Unit unit11 = new InfantryUnit("Footman", 100);
        Unit unit21 = new InfantryUnit("Grunt", 100);
        ArrayList<Unit> units1 = new ArrayList<>();
        ArrayList<Unit> units2 = new ArrayList<>();

        for(int i = 0; i<500; i++){
            units1.add(unit11);
            units2.add(unit21);
        }

        Army armyOne = new Army("armyOne", units1);
        Army armyTwo = new Army("armyTwo", units2);
        Battle battle = new Battle(armyOne, armyTwo);
        if (battle.simulate().equals(armyOne)){
            assert(armyOne.hasUnits());
            assert(!armyTwo.hasUnits());
        } else {
            assert(armyTwo.hasUnits());
            assert(!armyOne.hasUnits());
        }
    }
}