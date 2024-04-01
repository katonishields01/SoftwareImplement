//Shemar Brown (ID#)

import java.util.ArrayList;
import java.util.Scanner;

public class ResultsStorage {

    private ArrayList<Nurse> nurses;
    private ArrayList<LabTech> labTechs;
    private ArrayList<InventoryItem> inventory;
    private Scanner scanner;
    private int itemID;
    private String itemName;
    private String description;
    private int quantity;
    private double unitPrice;

    public ResultsStorage() {
        nurses = new ArrayList<>();
        labTechs = new ArrayList<>();
        inventory = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    

    private void initializeSystem() {
        System.out.println("Inventory System Initialized.");
        // Additional initialization logic can be added here.
    }

    private void addInventoryItem() {
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

    

    private void removeInventoryItem() {
        System.out.print("Enter Item ID to remove: ");
        int itemID = scanner.nextInt();

        boolean removed = inventory.removeIf(item -> item.itemID == itemID);

        if (removed) {
            System.out.println("Inventory item removed successfully.");
        } else {
            System.out.println("Inventory item not found with the given ID.");
        }
    }

    private void viewInventoryItemDetails() {
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

 

   
   

    

    private static class InventoryItem {
        
    }
}
