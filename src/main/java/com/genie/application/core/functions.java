package com.genie.application.core;

import com.genie.application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class functions {
    public static final String BASE_PATH = Paths.get("src").toAbsolutePath().normalize() + File.separator;

    public static String base_path(String path) {
        return BASE_PATH + path;
    }
}
