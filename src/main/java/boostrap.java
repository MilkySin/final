import core.App;
import core.Container;
import core.Database;
import core.Session;


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
