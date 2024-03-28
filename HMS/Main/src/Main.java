import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
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

  /*Admin enter = new Admin();
<<<<<<< HEAD
        enter.AdminMenu();*/
=======
        enter.AdminMenu();*/

        /*New person, who dis?*/

        //testinhg case
>>>>>>> 96ea1dec0ef132a847a3d8c9b767df08b3c238b4
