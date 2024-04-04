//Shemar Brown (ID#)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.ResultSet;
//import java.util.ArrayList;


public class ResultsStorage {

    private int patientid;
    private String patientf_name;
    private String patientl_name;
    private String test_done;
    private String description;
    private String results_col;

    private static final String url = "jdbc:mysql://localhost:3306/admin";
    private static final String user = "root";
    private static final String password = "SIProject2024";


    public void removeLabResults() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Patient ID to remove: ");
        patientid = Integer.parseInt(scanner.nextLine());

        // Remove results from database
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            //(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) 
            String query = "DELETE FROM results WHERE patientid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientid);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Results removed successfully.");
            } else {
                System.out.println("Results not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing Results: " + e.getMessage());
        }
        scanner.close();
    }

    //add results to database
    public void addLabResults() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Patient ID: ");
        patientid = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Patient's FName: ");
        patientf_name = scanner.nextLine();

        System.out.print("Enter Patient's LName: ");
        patientl_name = scanner.nextLine();

        System.out.print("Enter Test Done: ");
        test_done = scanner.nextLine();

        System.out.print("Enter Test Description: ");
        description = scanner.nextLine();

        System.out.print("Enter Results: ");
        results_col = scanner.nextLine();

        // Add inventory item to database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "INSERT INTO results (patientid, patientf_name, patientl_name, test_done, description, results_col) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientid);
            preparedStatement.setString(2,patientf_name );
            preparedStatement.setString(3, patientl_name);
            preparedStatement.setString(4, test_done);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, results_col);
           

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Results added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding Results: " + e.getMessage());
        }
        scanner.close();
    }
    //view lab results in database
    public void viewLabResults() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Patient ID to view details: ");
        patientid = Integer.parseInt(scanner.nextLine());

        // View details from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "SELECT * FROM results WHERE patientid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientid);

            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Results Details:");
                System.out.println("ID: " + resultSet.getInt("patientid"));
                System.out.println("Name: " + resultSet.getString("patientf_name") + resultSet.getString("patientl_name"));
                System.out.println("Test Done: " + resultSet.getString("test_done"));
                System.out.println("Description: " + resultSet.getString("description"));
                System.out.println("Final Results: " + resultSet.getString("results_col"));
            } else {
                System.out.println("Results not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing results details: " + e.getMessage());
        }
        scanner.close();
    }
}

