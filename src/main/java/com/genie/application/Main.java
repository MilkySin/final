package com.genie.application;

import com.genie.application.core.App;
import javafx.application.Application;
import javafx.stage.Stage;


import java.io.IOException;

import static com.genie.application.core.functions.BASE_PATH;
import static com.genie.application.core.functions.base_path;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        App.applicationName = Config.applicationName;
        App.setWindow(this, stage);
        App.scene("session.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

