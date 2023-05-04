package com.genie.application;

import java.util.HashMap;
import java.util.Map;

final class Config {
    static final HashMap<String, String> database = new HashMap<String, String>
            (
                    Map.of(
                            "database", "mysql",
                            "host", "localhost",
                            "dbname", "genie",
                            "port", "3306"
                    )
            );

    static final String APP_NAME = "Genie";
}
