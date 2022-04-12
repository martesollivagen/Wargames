module Wargames{
    requires javafx.controls;
    requires javafx.fxml;
    exports ntnu.idatt2001.martvaag;
    opens ntnu.idatt2001.martvaag to javafx.fxml;
}