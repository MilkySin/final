module com.example.hello2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hello2 to javafx.fxml;
    exports com.example.hello2;
    exports com.example.hello2.Controller;
    opens com.example.hello2.Controller to javafx.fxml;
}