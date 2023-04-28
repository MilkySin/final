import core.Database;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws Exception {
        var db = new Database(config.database, "root", "admin");
        System.out.println(db.isValid());
        ArrayList<String> cfg = new ArrayList<String>();
        cfg.add("task73");
        var data = db.query("Select * From user where password = ?", cfg).get();
        while (data.next()) {
            String Email = data.getString("email");
            String Password = data.getString("password");
            System.out.println(Email + " " + Password);
        }


//        RentalSys.Login();

//
////        Init objects
//        Regular regular = new Regular("C100", "John", "600", "911", "John", "1234", null);
//        Account admin = new Account("A100", "Test", "789", "911", "Admin", "admin", null);
//
//        RentalSys.Register(admin);
//        RentalSys.Register(regular);
//
//        RentalSys.Login();
    }
}

