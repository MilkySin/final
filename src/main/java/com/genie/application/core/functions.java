package com.genie.application.core;

import java.io.File;
import java.nio.file.Path;

public class functions {
    public static final String BASE_PATH = Path.of("").toAbsolutePath().normalize().toString();

    public static String base_path(String path) {
        return BASE_PATH + File.separator + path;
    }
}
