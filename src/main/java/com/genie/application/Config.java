package com.genie.application;

import java.util.HashMap;
import java.util.Map;

public final class Config {
    public static final HashMap<String, String> database = new HashMap<String, String>
            (
                    Map.of(
                            "database", "mysql",
                            "host", "localhost",
                            "dbname", "genie",
                            "port", "3306"
                    )
            );

    public static final String applicationName = "Genie";
}
