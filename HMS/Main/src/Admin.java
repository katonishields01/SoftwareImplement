import java.util.Scanner;

public class Admin {
    public void AdminMenu()
    {
        Scanner scanner = new Scanner(System.in); 
        int choice;
        do {
            System.out.println("    WELCOME ADMIN   ");
            System.out.println("      \n     Admin Menu     ");
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
                    System.out.println("You have chosen to Register Patient");
                    // Add your logic for registering a patient here
                    break;
                case 2:
                    System.out.println("You have chosen to Register Staff");
                    // Add your logic for registering a patient here
                    break;
                case 3:
                    System.out.println("You have chosen to Register Staff");
                    // Add your logic for registering a patient here
                    break;
                case 4:
                    System.out.println("You have chosen to Create Invoice");
                    // Add your logic for creating an invoice here
                    break;
                case 5:
                    System.out.println("You have chosen to Schedule Appointment");
                    // Add your logic for scheduling an appointment here
                    break;
                case 6:
                    System.out.println("You have chosen to View Patient Basic Information Records");
                    // Add your logic for viewing patient basic information records here
                    break;
                case 7:
                    System.out.println("You have chosen to Upload Prescription Order");
                    // Add your logic for uploading a prescription order here
                    break;
                case 8:
                    System.out.println("You have chosen to View Patient Medical History");
                    // Add your logic for viewing patient medical history here
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number from 1 to 7.");
                    break;
            }
            scanner.close();
        }while (choice != 9);
    }
}
