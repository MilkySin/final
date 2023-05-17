module com.example.hello2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hello2 to javafx.fxml;
    exports com.example.hello2;
    exports com.example.hello2.Reader;
    opens com.example.hello2.Reader to javafx.fxml;
    exports com.example.hello2.Controller.Items;
    opens com.example.hello2.Controller.Items to javafx.fxml;
    exports com.example.hello2.Controller.Users;
    opens com.example.hello2.Controller.Users to javafx.fxml;
    exports com.example.hello2.Controller.Display;
    opens com.example.hello2.Controller.Display to javafx.fxml;
}