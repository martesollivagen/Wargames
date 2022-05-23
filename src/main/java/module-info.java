module Wargames{
    requires javafx.controls;
    requires javafx.fxml;

    exports ntnu.idatt2001.martvaag.controller;
    opens ntnu.idatt2001.martvaag.controller to javafx.fxml;
    exports ntnu.idatt2001.martvaag.view;
    opens ntnu.idatt2001.martvaag.view to javafx.fxml;
    exports ntnu.idatt2001.martvaag.model.battle;
    opens ntnu.idatt2001.martvaag.model.battle to javafx.fxml;
    exports ntnu.idatt2001.martvaag.model.animation;
    opens ntnu.idatt2001.martvaag.model.animation to javafx.fxml;
    exports ntnu.idatt2001.martvaag.model.unit;
    opens ntnu.idatt2001.martvaag.model.unit to javafx.fxml;
    exports ntnu.idatt2001.martvaag.model.tools.filehandling;
    opens ntnu.idatt2001.martvaag.model.tools.filehandling to javafx.fxml;
    exports ntnu.idatt2001.martvaag.model.tools.enums;
    opens ntnu.idatt2001.martvaag.model.tools.enums to javafx.fxml;
    exports ntnu.idatt2001.martvaag.model.tools.factory;
    opens ntnu.idatt2001.martvaag.model.tools.factory to javafx.fxml;
    exports ntnu.idatt2001.martvaag.model.tools.observer;
    opens ntnu.idatt2001.martvaag.model.tools.observer to javafx.fxml;
}