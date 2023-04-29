//import java.io.*;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Scanner;
//import java.util.stream.Stream;
//
//public class RentalSys {
//
//    public RentalSys(File file) {
//        try (BufferedReader reader = Files.newBufferedReader(file);
//             Stream<String> lines = reader.lines();) {
//
//            long count = lines.count();
//            System.out.println("count = " + count);
//        }
//    }
//
//    static HashSet<String> hs = new HashSet<>();
//
//
//    public static void Register(Account account) throws IOException {
//        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//        hs.add(account.getUsername() + account.getPass());
//        for(String s : hs){
//            bw.write(s + "\n");
//        }
//        bw.close();
//    }
//    public static void Login() throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader(file));
//
//        String st;
//        ArrayList<String> accounts = new ArrayList<>();
//
//        while ((st = br.readLine()) != null){
//            accounts.add(st);
//        }
//
//
//        //Login
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Username: ");
//            String user_input = scanner.nextLine();
//
//            System.out.print("Password: ");
//            String pass_input = scanner.nextLine();
//
//            if (accounts.contains(user_input + pass_input)) {
//                System.out.println("Login success");
//                br.close();
//                break;
//            } else {
//                System.out.println("Invalid username or password ");
//            }
//        }
//    }
//
//    public static void addRental(Account account, HashSet<items> listofrental){
//        account.setListofrental(listofrental);
//    }
//
//    public static void removeRental(Account account, items rentalItem){
//        account.getListofrental().remove(rentalItem);
//    }
//}
