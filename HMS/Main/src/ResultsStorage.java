//Shemar Brown (ID#)

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;


public class ResultsStorage {

    private static final String url = "jdbc:mysql://localhost:3306/admin";
    private static final String user = "root";
    private static final String password = "SIProject2024";

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
    public void connect(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM service WHERE itemname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, itemName);

            ResultSet resultSet = preparedStatement.executeQuery();

        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
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

 ////////////////////////////////////////////////////////////////

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
    
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
    
        // Add inventory item to database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_db", "username", "password")) {
            String query = "INSERT INTO inventory (item_id, item_name, description, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item.itemID);
            preparedStatement.setString(2, item.itemName);
            preparedStatement.setString(3, item.description);
            preparedStatement.setInt(4, item.quantity);
            preparedStatement.setDouble(5, item.unitPrice);
    
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inventory item added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding inventory item: " + e.getMessage());
        }
    }
    
   

    

    private static class InventoryItem {
        
    }
}
