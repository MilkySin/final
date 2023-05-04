package com.genie.application;

import com.genie.application.core.App;
import javafx.application.Application;
import javafx.stage.Stage;


import java.io.IOException;
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Boostrap.run();
        App.bindWindow(this, stage);
//        App.redirect("session.fxml");
            App.redirect("dataview.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

