package com.dreamcatcher.genie.app;

import com.dreamcatcher.genie.app.core.App;
import com.dreamcatcher.genie.app.core.Container;
import com.dreamcatcher.genie.app.core.Database;
import com.dreamcatcher.genie.app.core.Session;


public class boostrap {
    private static final Container container = new Container();
    public static void run() {

        container.bind("Core/Database", () -> {
            try {
                return new Database(config.database, "root", "admin");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        container.bind("Core/Session", Session::new);

        App.setContainer(container);
    }
}
