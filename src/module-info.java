module GameStoreJavaFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires json.simple;

    opens GameStore;

    exports GameStore;
}