package ntnu.idatt2001.martvaag.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for SupportUnit class
 * @version 2022-05-19
 * @author martvaag
 */
class SupportTest {

    @Test
    @DisplayName("Support unit heal another unit")
    void supportUnitAdds50ToRangedUnitsHealth() {
        Unit healer = new SupportUnit("Healer", 100);
        Unit rangedUnit = new RangedUnit("Attacker", 100);
        healer.heal(rangedUnit);
        assertEquals(150,rangedUnit.getHealth());
    }
}