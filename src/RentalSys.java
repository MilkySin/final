import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalSys {
    static String home = System.getProperty("user.home");
    static File file = new File(
            home + File.separator + "IdeaProjects" + File.separator + "final" + File.separator + "src" + File.separator + "usernamepass.txt");

    public static void Login() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        ArrayList<String> accounts = new ArrayList<>();

        while ((st = br.readLine()) != null){
            accounts.add(st);
        }

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


}
