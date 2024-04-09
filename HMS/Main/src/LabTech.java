//Shemar Brown (2008220)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LabTech {
    private int labTechID;
    private String labTechFName;
    private String labTechLName;
    private String userName;
    private String pass;
    private String userType;
    private int number;


    public void labTechMenu() {
        Scanner scanner = new Scanner(System.in); 
        int choice;
        do {
            System.out.println("\tWELCOME");
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
                    ResultsStorage re1 = new ResultsStorage() ;
                    re1.addLabResults();
                    break;
                case 2:
                    ResultsStorage re2 = new ResultsStorage();
                    re2.removeLabResults();;
                    break;
                case 3:
                    ResultsStorage re3 = new ResultsStorage();
                    re3.viewLabResults();
                    break;
                case 4:
                System.out.println("Exiting...");
                System.out.println("Logged out Successfully");
                Main in = new Main();
                in.Home();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    labTechMenu();
            }
        }while (choice != 4);
        //scanner.close();
    }

    public void addLabTech() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Lab Technician ID: ");
        labTechID = Integer.parseInt(scanner.nextLine());
        //scanner.nextLine();

        System.out.print("Enter User Type: ");
        userType = scanner.nextLine();

        System.out.print("Enter Lab Technician First Name: ");
        labTechFName = scanner.nextLine();

        System.out.print("Enter Lab Technician Last Name: ");
        labTechLName = scanner.nextLine();

        System.out.print("Enter Lab Technician Username: ");
        userName = scanner.nextLine();

        System.out.print("Enter Lab Technician Password: ");
        pass = scanner.nextLine();

        System.out.print("Enter Lab Technician Contact: ");
        number = Integer.parseInt(scanner.nextLine());

        // Add lab technician to database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "INSERT INTO users (id, username, password, user_type, f_name, l_name, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, labTechID);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, pass);
            preparedStatement.setString(4, userType);
            preparedStatement.setString(5, labTechFName);
            preparedStatement.setString(6, labTechLName);
            preparedStatement.setInt(7, number);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Lab Technician added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding lab technician: " + e.getMessage());
        }
        //scanner.close();
    }

    public void removeLabTech() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Lab Technician ID to remove: ");
        labTechID = Integer.parseInt(scanner.nextLine());

        // Remove lab technician from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, labTechID);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Lab Technician removed successfully.");
            } else {
                System.out.println("Lab Technician not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing lab technician: " + e.getMessage());
        }
        //scanner.close();
    }

    public void viewLabTechDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Lab Technician ID to view details: ");
        labTechID = Integer.parseInt(scanner.nextLine());

        // View lab technician details from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, labTechID);

            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Lab Technician Details:");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Full Name: " + resultSet.getString("f_name") + " " + resultSet.getString("l_name"));
                System.out.println("Username " + resultSet.getString("username"));
                System.out.println("Password: " + resultSet.getString("password"));
                System.out.println("User Type: " + resultSet.getString("user_type"));
                System.out.println("Contact: " + resultSet.getInt("contact"));
            } else {
                System.out.println("Lab Technician not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing lab technician details: " + e.getMessage());
        }
        //scanner.close();
    }
}


