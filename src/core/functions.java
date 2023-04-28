package core;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

public class functions {
    public static final String BASE_PATH = Paths.get("src").toAbsolutePath().normalize() + File.separator;

    public static String base_path(String path) {
        return BASE_PATH + path;
    }

}
