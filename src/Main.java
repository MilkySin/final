import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String home = System.getProperty("user.home");
    static File file = new File(
            home + File.separator + "IdeaProjects" + File.separator + "final" + File.separator + "src" + File.separator + "usernamepass.txt");

    public static void main(String[] args) throws Exception {
//        RentalSys.Login();

        //Init objects
        Regular regular = new Regular("C100", "John", "600", "911", "John", "1234", null);
        Account admin = new Account("A100", "Test", "789", "911", "Admin", "admin", null);

        RentalSys.Register(admin);
        RentalSys.Register(regular);

        RentalSys.Login();
    }
}

