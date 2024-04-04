<<<<<<< HEAD
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
=======
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
            int input;
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
                        do {
                            System.out.print("Please Select: ");
                            System.out.println("\n1. Patient");
                            System.out.println("2. Staff");
                            System.out.println("3. Exit");
                            input = scanner.nextInt();
                        switch (input) {
                            case 1:
                            System.out.println("Creating New Patient Account");
                            Patient p1 = new Patient();
                            p1.addPatient();
                            System.out.println("Account Created Successfully");  
                            break;
                            case 2:
                            System.out.println("Please Log Admin or Contact Admin to Continue.");
                            Main in2 = new Main();
                            in2.Home();   
                            break;
                            case 3:
                            System.out.println("Exiting...");
                            Main in3 = new Main();
                            in3.Home();
                            break;
                            default:
                            System.out.println("Invalid option. Please choose a number from 1 to 3.");
                            Main in4 = new Main();
                            in4.Home();
                            break;
                        }
                            choice = scanner.nextInt();
                        }while(true && input != 3);
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number from 1 to 3.");
                        Main in5 = new Main();
                        in5.Home();
                        break;
                }
                
            }while (true && choice != 2);
            scanner.close();
        }
    }

  
>>>>>>> d55682477474272e21b45f7509a52f82584e6808
