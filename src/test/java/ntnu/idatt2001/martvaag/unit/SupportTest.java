package ntnu.idatt2001.martvaag.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for SupportUnit class
 * @version 2022-05-22
 * @author martvaag
 */
public class SupportTest {

    @Test
    @DisplayName("Support unit heal units")
    public void supportUnitAdds100ToTwoUnitsHealth() {
        Unit healer = new SupportUnit("Healer", 100);
        Unit rangedUnit = new RangedUnit("Attacker", 100);
        Unit infantryUnit = new InfantryUnit("Attacker", 100);
        healer.heal(rangedUnit, infantryUnit);
        assertEquals(200,rangedUnit.getHealth());
        assertEquals(200, infantryUnit.getHealth());
    }
}