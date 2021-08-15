module GameStoreJavaFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens GameStore;
    opens GameStore.Classes;
    opens GameStore.Controllers;
    opens GameStore.FXMLs;
    opens GameStore.Resources;

    exports GameStore;
    exports GameStore.Classes;
    exports GameStore.Controllers;
}