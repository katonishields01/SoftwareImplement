import java.util.ArrayList;
import java.util.Scanner;

public class LabTech {
    private int labTechID;
    private String labTechName;
    private String shift;
    private ArrayList<LabTech> labTechs;
    private Scanner scanner;
    public void labTechMenu() {
        Scanner scanner = new Scanner(System.in); 
        int choice;
        do {
            System.out.println("\tWELCOME")
            System.out.println("\n\tMenu Options:");
            System.out.println("1. Add Lab Results");
            System.out.println("2. Remove Lab Results");
            System.out.println("3. View Lab Results");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addLabResults();
                    break;
                case 2:
                    removeLabResults();
                    break;
                case 3:
                    viewLabResults();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    Login in = new Login();
                    in.User();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            scanner.close();
        }while (choice != 5);

    } 
    
    private void addLabTech() {
        LabTech labTech = new LabTech();

        System.out.print("Enter Lab Technician ID: ");
        labTech.labTechID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Lab Technician Name: ");
        labTech.labTechName = scanner.nextLine();

        System.out.print("Enter Lab Technician Shift: ");
        labTech.shift = scanner.nextLine();

        labTechs.add(labTech);
        System.out.println("Lab Technician added successfully.");
    }


    private void removeLabTech() {
        System.out.print("Enter Lab Technician ID to remove: ");
        int labTechID = scanner.nextInt();

        boolean removed = labTechs.removeIf(labTech -> labTech.labTechID == labTechID);

        if (removed) {
            System.out.println("Lab Technician removed successfully.");
        } else {
            System.out.println("Lab Technician not found with the given ID.");
        }
    }

    private void viewLabTechDetails() {
        System.out.print("Enter Lab Technician ID to view details: ");
        int labTechID = scanner.nextInt();

        for (LabTech labTech : labTechs) {
            if (labTech.labTechID == labTechID) {
                System.out.println("Lab Technician Details:");
                System.out.println("ID: " + labTech.labTechID);
                System.out.println("Name: " + labTech.labTechName);
                System.out.println("Shift: " + labTech.shift);
                return;
            }
        }

        System.out.println("Lab Technician not found with the given ID.");
    }

    public void addLabResults() {
        InventoryItem item = new InventoryItem();

        System.out.print("Enter Item ID: ");
        item.itemID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter Item Name: ");
        item.itemName = scanner.nextLine();

        System.out.print("Enter Item Description: ");
        item.description = scanner.nextLine();

        System.out.print("Enter Item Quantity: ");
        item.quantity = scanner.nextInt();

        System.out.print("Enter Item Unit Price: ");
        item.unitPrice = scanner.nextDouble();

        inventory.add(item);
        System.out.println("Inventory item added successfully.");
    }

    public void removeLabResults() {
        System.out.print("Enter Item ID to remove: ");
        int itemID = scanner.nextInt();

        boolean removed = inventory.removeIf(item -> item.itemID == itemID);

        if (removed) {
            System.out.println("Inventory item removed successfully.");
        } else {
            System.out.println("Inventory item not found with the given ID.");
        }
    }

    public void viewLabResults() {
        System.out.print("Enter Item ID to view details: ");
        int itemID = scanner.nextInt();

        for (InventoryItem item : inventory) {
            if (item.itemID == itemID) {
                System.out.println("Inventory Item Details:");
                System.out.println("ID: " + item.itemID);
                System.out.println("Name: " + item.itemName);
                System.out.println("Description: " + item.description);
                System.out.println("Quantity: " + item.quantity);
                System.out.println("Unit Price: " + item.unitPrice);
                return;
            }
        }

        System.out.println("Inventory item not found with the given ID.");
    }

    private static class LabTech {
        private int labTechID;
        private String labTechName;
        private String shift;
    }

}
