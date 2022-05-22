module Wargames{
    requires javafx.controls;
    requires javafx.fxml;

    exports ntnu.idatt2001.martvaag.unit;
    opens ntnu.idatt2001.martvaag.unit to javafx.fxml;
    exports ntnu.idatt2001.martvaag.controllers;
    opens ntnu.idatt2001.martvaag.controllers to javafx.fxml;
    exports ntnu.idatt2001.martvaag.tools.observer;
    opens ntnu.idatt2001.martvaag.tools.observer to javafx.fxml;
    exports ntnu.idatt2001.martvaag.tools.enums;
    opens ntnu.idatt2001.martvaag.tools.enums to javafx.fxml;
    exports ntnu.idatt2001.martvaag.tools.filehandling;
    opens ntnu.idatt2001.martvaag.tools.filehandling to javafx.fxml;
    exports ntnu.idatt2001.martvaag.tools.factory;
    opens ntnu.idatt2001.martvaag.tools.factory to javafx.fxml;
    exports ntnu.idatt2001.martvaag.battle;
    opens ntnu.idatt2001.martvaag.battle to javafx.fxml;
    exports ntnu.idatt2001.martvaag.application;
    opens ntnu.idatt2001.martvaag.application to javafx.fxml;
}