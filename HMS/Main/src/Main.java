//import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //Scanner scanner = new Scanner(System.in); 
        //int choice;
        
        Doctor doc = new Doctor();
        doc.doctorMenu();
    }
}


  /*Admin enter = new Admin();
        enter.AdminMenu();*/
/*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean consultationDocumented = false;
        String patientId = "";
        String requestStatus = "";
        String labResults = ""; */
/*do {
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
    }*/