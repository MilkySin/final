import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RentalSys {
    static String home = System.getProperty("user.home");
    static File file = new File(
            home + File.separator + "IdeaProjects" + File.separator + "final" + File.separator + "src" + File.separator + "usernamepass.txt");
    static HashSet<String> hs = new HashSet<>();


    public static void Register(Account account) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        hs.add(account.getUsername() + account.getPass());
        for(String s : hs){
            bw.write(s + "\n");
        }
        bw.close();
    }
    public static void Login() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        ArrayList<String> accounts = new ArrayList<>();

        while ((st = br.readLine()) != null){
            accounts.add(st);
        }


        //Login
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Username: ");
            String user_input = scanner.nextLine();

            System.out.print("Password: ");
            String pass_input = scanner.nextLine();

            if (accounts.contains(user_input + pass_input)) {
                System.out.println("Login success");
                br.close();
                break;
            } else {
                System.out.println("Invalid username or password ");
            }
        }
    }

    public static void addRental(Account account, HashSet<items> listofrental){
        account.setListofrental(listofrental);
    }

    public static void removeRental(Account account, items rentalItem){
        account.getListofrental().remove(rentalItem);
    }
}
