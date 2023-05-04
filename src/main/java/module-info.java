module com.genie.application {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    requires bcrypt;
    requires java.sql;

    opens com.genie.application to javafx.fxml;
    exports com.genie.application;

    exports com.genie.application.controllers;
    opens com.genie.application.controllers to javafx.fxml;

}