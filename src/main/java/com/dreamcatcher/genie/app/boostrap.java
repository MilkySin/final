package com.dreamcatcher.genie.app;

import com.dreamcatcher.genie.app.core.App;
import com.dreamcatcher.genie.app.core.Container;
import com.dreamcatcher.genie.app.core.Database;


public class boostrap {

    public static void run() {
        var container = new Container();

        container.bind("Core/Database", () -> {
            try {
                return new Database(config.database, "root", "admin");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

//        container.bind("Core/Session", Session::new);

        App.setContainer(container);
    }
}
