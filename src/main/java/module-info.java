module com.dreamcatcher.genie.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    requires bcrypt;
    requires java.sql;

    opens com.dreamcatcher.genie.app to javafx.fxml;
    exports com.dreamcatcher.genie.app;

    exports com.dreamcatcher.genie.app.controllers;
    opens com.dreamcatcher.genie.app.controllers to javafx.fxml;

}