package com.genie.application.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class App {
    public static String applicationName;
    private static String resourcePath;
    private static Stage stage;

    public static void setWindow(Object object, Stage window) {
        App.resourcePath = object.getClass().getResource("").getPath();
        App.stage = window;
    }

    public static void scene(String resource) {
        Scene scene = null;
        try {
            var fxmlLoader = new FXMLLoader(new URL("file:" + resourcePath + "view" + File.separator + resource));
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void setTitle(String title) {
        stage.setTitle(title);
    }

}
