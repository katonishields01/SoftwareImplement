//Katoni Shileds(2003903)
import java.util.Scanner;

public class Admin {
    public void AdminMenu()
    {
        Scanner scanner = new Scanner(System.in); 
        int choice;
        do {
            System.out.println("\t******WELCOME ADMINISTRATOR MENU******");
            System.out.println("\n1. Register Patient");
            System.out.println("2. Register Staff");
            System.out.println("3. Remove Staff");
            System.out.println("4. Create Invoice");
            System.out.println("5. Schedule Appointment");
            System.out.println("6. View Patient Personal Information Records");
            System.out.println("7. Upload Prescription Order");
            System.out.println("8. View Patient Medical History");
            System.out.println("9. Exit");
            System.out.print("Select choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //logic for registering a patient
                    System.out.println("You have chosen to Register Patient");
                    
                    break;
                case 2:
                    //logic for registering a staff 
                    System.out.println("You have chosen to Register Staff");
                    
                    break;
                case 3:
                    //logic for creating an invoice 
                    System.out.println("You have chosen to Create Invoice");
                    
                    break;
                case 4:
                    //logic for scheduling an appointment 
                    System.out.println("You have chosen to Schedule Appointment");
                    
                    break;
                case 5:
                    //logic for viewing patient basic information records
                    System.out.println("You have chosen to View Patient Basic Information Records");
                    
                    break;
                case 6:
                    // logic for uploading a prescription order 
                    System.out.println("You have chosen to Upload Prescription Order");
                    
                    break;
                case 7:
                    //logic for viewing patient medical history 
                    System.out.println("You have chosen to View Patient Medical History");
                    
                    break;
                case 8:
                System.out.println("Exiting...");
                //System.exit(0);
                System.out.println("Logged out Successfully");
                Main in = new Main();
                in.Home();
                default:
                    System.out.println("Invalid option. Please choose a number from 1 to 7.");
                    break;
            }
            scanner.close();
        }while (choice != 8);
    }
}
