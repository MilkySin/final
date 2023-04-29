package com.dreamcatcher.genie.app;

import java.util.HashMap;
import java.util.Map;

public final class config {
    static final HashMap<String, String> database = new HashMap<String, String>
            (
                    Map.of(
                            "database", "mysql",
                            "host", "localhost",
                            "dbname", "genie",
                            "port", "3306"
                    )
            );
}
