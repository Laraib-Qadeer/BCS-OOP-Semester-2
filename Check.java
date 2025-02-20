import java.util.Scanner;

public class Check {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your username: ");
        String inputUsername = scanner.nextLine();
        
        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();
        
        if (UserInfo.CheckUserInfo(inputUsername, inputPassword)) {
            System.out.println("LOG-IN SUCCESSFUL!");
        } else {
            System.out.println("Incorrect username or password.");
        }
        
        scanner.close();
    }
}
