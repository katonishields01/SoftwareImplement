import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Main in = new Main();
        in.Home();
    }

    public void Home() 
        {
            Scanner scanner = new Scanner(System.in); 
            int choice;
            do {
                System.out.print("Please Select: ");
                System.out.println("\n1. Log-In");
                System.out.println("2. Sign-Up");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Log-In Selected");
                        Login user = new Login();
                        user.User();
                        break;
                    case 2:
                        System.out.println("Creating New Account");
                        // Add New Account
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number from 1 to 2.");
                        break;
                }
                scanner.close();
            }while (choice != 2);
        }
    }

  
