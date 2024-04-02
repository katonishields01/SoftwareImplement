//Katoni Shileds(2003903)
import java.util.Scanner;

public class Admin {
    public void AdminMenu()
    {
        Scanner scanner = new Scanner(System.in); 
        int choice;
        int input;
        do {
            System.out.println("\t******WELCOME ADMINISTRATOR MENU******");
            System.out.println("\n1. Register Patient");
            System.out.println("2. Register Staff");
            System.out.println("3. Create Invoice");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. View Patient Personal Information Records");
            System.out.println("6. Upload Prescription Order");
            System.out.println("7. View Patient Medical History");
            System.out.println("8. Exit");
            System.out.print("Select choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //logic for registering a patient
                    System.out.println("You have chosen to Register Patient");
                    do {
                        System.out.println("\n1. Register Patient");
                        System.out.println("2. Update Patient");
                        System.out.println("3. Remove Patient");
                        System.out.println("4. Exit");
                        System.out.print("Select choice: ");
                        input = scanner.nextInt();
                        switch (input) {
                            case 1:
                                
                            break;

                            case 2:
                                
                            break;
                            case 3:
                                
                            break;
                            case 4:
                            System.out.println("Exiting...");
                            AdminMenu();
                            break;
                            default:
                            System.out.println("Invalid option. Please choose a number from 1 to 7.");
                                break;
                        }
                    } while (input != 4);
                    break;
                case 2:
                    //logic for registering a staff 
                    System.out.println("You have chosen to Register Staff");
                    do {
                        System.out.println("\n1. Register Staff");
                        System.out.println("2. Update Staff");
                        System.out.println("3. Remove Staff");
                        System.out.println("4. Exit");
                        System.out.print("Select choice: ");
                        input = scanner.nextInt();
                        switch (input) {
                            case 1:
                                
                            break;

                            case 2:
                                
                            break;
                            case 3:
                                
                            break;
                            case 4:
                            System.out.println("Exiting...");
                            AdminMenu();
                            break;
                            default:
                            System.out.println("Invalid option. Please choose a number from 1 to 7.");
                                break;
                        }
                    } while (input != 4);
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
                    System.out.println("Invalid option. Please choose a number from 1 to 8.");
                    AdminMenu();
                    break;
            }
            scanner.close();
        }while (choice != 8);
    }
}
