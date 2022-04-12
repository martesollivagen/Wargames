package ntnu.idatt2001.martvaag;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class SimulateBattleController {
    @FXML Label BattleText;
    @FXML Label battleWinner;

    public void battle(ActionEvent event) throws IOException {
        Army army1 = Army.readArmyFromFile(CreateArmyController.getFilePathArmy1());
        Army army2 = Army.readArmyFromFile(CreateArmyController.getFilePathArmy2());

        Battle battle = new Battle(army1, army2);
        BattleText.setText("");
        battleWinner.setText("Winner of this battle: " + battle.simulate().toString());
    }
}
