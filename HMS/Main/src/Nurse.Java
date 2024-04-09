//Shemar Brown(2008220)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class Nurse {

    private int nurseID;
    private String nurseFName;
    private String nurseLName;
    private String userName;
    private String pass;
    private String userType;
    private int number;
    private String DOB;
    private String height;
    private String weight;
    private int age;
    private static final String url = "jdbc:mysql://localhost:3306/admin";
    private static final String user = "root";
    private static final String password = "SIProject2024";


    public void nurseMenu() {
        Scanner scanner = new Scanner(System.in); 
        int choice;
        do {
            System.out.println("----------------------");
            System.out.println("\tWELCOME");
            System.out.println("\n\tMenu Options:");
            System.out.println("1. Add Lab Results");
            System.out.println("2. View Lab Results");
            System.out.println("3. Record Assessment");
            System.out.println("4. View Diagnosis");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            System.out.println("----------------------");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ResultsStorage re1 = new ResultsStorage() ;
                    re1.addLabResults();
                    break;

                case 2:
                    ResultsStorage re3 = new ResultsStorage();
                    re3.viewLabResults();
                    break;

                case 3:
                    //code to patient for height, age, weight dob
                    System.out.println("----------------------");
                    System.out.println("Enter Patient's TRN/ID#: ");
                    int patientId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter Patient's age: ");
                    age = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter Patient's height: ");
                    height = scanner.nextLine();
                    System.out.println("Enter Patient's weight: ");
                    weight= scanner.nextLine();
                    System.out.println("Enter Patient's D.O.B: ");
                    DOB = scanner.nextLine();
                        try {
                        Connection connection = DriverManager.getConnection(url, user, password);
                        String query = "UPDATE results SET age =?, height =?, weight =?, dob =? WHERE patientid =?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, age);
                        preparedStatement.setString(2, height);
                        preparedStatement.setString(3, weight);
                        preparedStatement.setString(4, DOB);
                        preparedStatement.setInt(5, patientId);
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Assessment updated successfully.");
                        }
                        else {
                            System.out.println("Assessment updated successfully. ID not found.");

                        }
                    } catch (SQLException e) {
                        System.out.println("Error updating patient's information: " + e.getMessage());
                    }
                    System.out.println("----------------------");
                    break;

                case 4:
                System.out.println("----------------------");
                System.out.println("Enter PatientID  to view details: ");
                patientId = Integer.parseInt(scanner.nextLine());
        
                // View nurse details from database
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
                    String query = "SELECT * FROM results WHERE id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, patientId);
        
                    java.sql.ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        System.out.println("Patient's Details:");
                        System.out.println("ID: " + resultSet.getInt("patientid"));
                        System.out.println("Full Name: " + resultSet.getString("patientf_name") + " " + resultSet.getString("patientl_name"));
                        System.out.println("Symptoms " + resultSet.getString("symptom"));
                        System.out.println("Diagnosis: " + resultSet.getString("diagnosis"));
                        System.out.println("Prescription: " + resultSet.getString("prescription"));
                        System.out.println("Doctor: " + resultSet.getString("doctorName"));
                    } else {
                        System.out.println("Patient not found with the given ID.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error viewing Patient details: " + e.getMessage());
                }
                System.out.println("----------------------");
                //scanner.close();
                break;
                case 5:
                System.out.println("Exiting...");
                System.out.println("Logged out Successfully");
                System.out.println("----------------------");
                Main in = new Main();
                in.Home();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    nurseMenu();
            }
        }while (choice != 5);
        scanner.close();
    }
    //Add new nurse
    public void addNurse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("Enter Nurse ID: ");
        nurseID = Integer.parseInt(scanner.nextLine());
       

        System.out.print("Enter User Type: ");
        userType = scanner.nextLine();

        System.out.print("Enter Nurse First Name: ");
        nurseFName = scanner.nextLine();

        System.out.print("Enter Nurse Last Name: ");
        nurseLName = scanner.nextLine();

        System.out.print("Enter Nurse Username: ");
        userName = scanner.nextLine();

        System.out.print("Enter Nurse Password: ");
        pass = scanner.nextLine();

        System.out.print("Enter Nurse Contact: ");
        number = Integer.parseInt(scanner.nextLine());

        // Add nurse to database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "INSERT INTO users (id, username, password, user_type, f_name, l_name, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, nurseID);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, pass);
            preparedStatement.setString(4, userType);
            preparedStatement.setString(5, nurseFName);
            preparedStatement.setString(6, nurseLName);
            preparedStatement.setInt(7, number);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nurse added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding Nurse: " + e.getMessage());
        }
        //scanner.close();
        System.out.println("----------------------");
    }

    public void removeNurse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("Enter Nurse ID to remove: ");
        nurseID = Integer.parseInt(scanner.nextLine());
        

        // Remove nurse from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, nurseID);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Nurse removed successfully.");
            } else {
                System.out.println("Nurse not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing Nurse: " + e.getMessage());
        }
        //scanner.close();
    }

    public void viewNurseDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("Enter Nurse ID to view details: ");
        nurseID = Integer.parseInt(scanner.nextLine());

        // View nurse details from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, nurseID);

            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Nurse Details:");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Full Name: " + resultSet.getString("f_name") + " " + resultSet.getString("l_name"));
                System.out.println("Username " + resultSet.getString("username"));
                System.out.println("Password: " + resultSet.getString("password"));
                System.out.println("User Type: " + resultSet.getString("user_type"));
                System.out.println("Contact: " + resultSet.getInt("contact"));
            } else {
                System.out.println("Nurse not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing nurse details: " + e.getMessage());
        }
        //scanner.close();
        System.out.println("----------------------");
    }
}