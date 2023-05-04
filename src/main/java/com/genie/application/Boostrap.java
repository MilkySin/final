package com.genie.application;

import com.genie.application.core.App;
import com.genie.application.core.Authenticator;
import com.genie.application.core.Container;
import com.genie.application.core.Database;


public class Boostrap {

    public static void run() {
        var container = new Container();

        container.bind("Core/Database", () -> {
            try {
                return new Database(Config.database, "root", "admin");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        container.bind("Core/Authenticator", () -> {
            try {
                return new Authenticator(container.resolve("Core/Database", Database.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        App.setContainer(container);
        App.APP_NAME = Config.APP_NAME;
    }
}
