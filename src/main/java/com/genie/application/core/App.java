package com.genie.application.core;

import com.genie.application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Supplier;

public class App {
    public static String APP_NAME = null;

    private static Object reference = null;
    private static Stage stage = null;
    private static Container container = null;

    public static void bindWindow(Object reference, Stage window) {
        App.reference = reference;
        App.stage = window;
    }

    public static void redirect(String resource, double width, double height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(reference.getClass().getResource(resource));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void redirect(String resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(reference.getClass().getResource(resource));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static Container getContainer() {
        return App.container;
    }

    public static void setContainer(Container container) {
        App.container = container;
    }

    public static void bind(String key, Supplier<?> resolver) {
        App.container.bind(key, resolver);
    }

    public static <T> T resolve(String key, Class<T> type) throws Exception {
        return App.container.resolve(key, type);
    }

}
