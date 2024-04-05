//Chevon Sutherland (ID#)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Patient {
    private int patientID;
    private String patientFName;
    private String patientLName;
    private String userName;
    private String pass;
    private String userType;
    private int number;

    public void patientMenu() 
    {
        Scanner scanner = new Scanner (System.in);
        String requestAppointment,requestPrescription;
        float invoice_Bal;
        int choice;

        System.out.println("Welcome to our Hospital Management Patient System!");
        do {
            System.out.println("Please select your choice from the Menu below: ");
            System.out.println("1. Request an appointment");
            System.out.println("2. Request lab results");
            System.out.println("3. Request a prescription");
            System.out.println("4. Pay invoice balance");
            System.out.println("5. Exit");
            System.out.println("Select your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                /* Case 1 to allow user to input preferred appointment date */
                case 1:
                System.out.println("Enter your preferred appointment date and time: ");
                requestAppointment = scanner.nextLine();
                if (val_Date_Time(requestAppointment))
                {
                    System.out.println("Appointment successfully scheduled for " + requestAppointment);
                } 
                else {
                    System.out.println("Invalid format. Please use yyyy-MM-dd HH:mm format");
                }
                break;

                /* Case 2 to allow user to request for lab results */
                case 2:
                ResultsStorage re1 = new ResultsStorage();
                re1.viewLabResults();
                
                /* Case 3 to allow user to request for medication */
                case 3:
                System.out.println("Enter the medication name: ");
                requestPrescription = scanner.nextLine();
                if (val_Medication_Name(requestPrescription))
                {
                    System.out.println("Prescription for" + requestPrescription + "successfully requested.");
                }
                else {
                    System.out.println("Invalid medication name");
                }
                break;

                /* Case 4 to allow user to pay for invoice */
                case 4:
                System.out.println("Enter the amount to pay: ");
                invoice_Bal = scanner.nextFloat();
                if (Val_Amount(invoice_Bal) && invoice_Bal > 0)
                {
                    System.out.println("Payment of $" + invoice_Bal + "successfully processed");
                } 
                else {
                    System.out.println("Invalid Amount.");
                }
                break;
                
                /* Case 5 to allow user to exit system */
                case 5:
                System.out.println("Exiting...");
                System.out.println("Logged out Successfully");
                Main in = new Main();
                in.Home();
                    break;
                /* Defaults in the case user enters invalid choice */
                default:
                System.out.println("Invalid Choice. Please select a valid choice.");
            }
        }while (choice != 5);
        scanner.close();
    }

    /* Method to handle validation and processing of appointment request */
    private static boolean val_Date_Time(String requestAppointment) 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try{
            LocalDateTime.parse(requestAppointment, formatter);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /*Implementation to validate the amount */
    private static boolean Val_Amount(float invoice_Bal) 
    {
    	if (invoice_Bal <= 0) {
    		System.out.println("Invalid amount: Amount must be positive.");
            return false;
        }
        System.out.println("Payment of $" + invoice_Bal + " successfully processed");
        return true;
    }

    /*Implementation to validate the medication name  */
    private static boolean val_Medication_Name(String requestPrescription) 
    {
    	if (requestPrescription.isEmpty()) {
            return false;
        }
    	if (requestPrescription.matches(".*[^a-zA-Z0-9\\s].*")) {
            return false;
        }
    	if (requestPrescription.length() > 50) {
            return false;
        }
    	return true;
    }

    //View Patient Details
    public void viewPatientDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Patient TRN to view details: ");
        patientID = Integer.parseInt(scanner.nextLine());

        // View patient details from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientID);

            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Patient Details:");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Full Name: " + resultSet.getString("f_name") + resultSet.getString("l_name"));
                System.out.println("Username " + resultSet.getString("username"));
                System.out.println("Password: " + resultSet.getString("password"));
                System.out.println("User Type: " + resultSet.getString("user_type"));
                System.out.println("Contact: " + resultSet.getInt("contact"));
            } else {
                System.out.println("Patient not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing Patient details: " + e.getMessage());
        }
        scanner.close();
    }

    //Add new Patient
    public void addPatient() {
        Scanner scanner = new Scanner(System.in);
        if(userType != "patient") {
            System.out.print("Enter Patient TRN: ");
            patientID = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter User Type: ");
            userType = scanner.nextLine();

            System.out.print("Enter Patient First Name: ");
            patientFName = scanner.nextLine();

            System.out.print("Enter Patient Last Name: ");
            patientLName = scanner.nextLine();

            System.out.print("Enter Patient Username: ");
            userName = scanner.nextLine();

            System.out.print("Enter Patient Password: ");
            pass = scanner.nextLine();

            System.out.print("Enter Patient Contact: ");
            number = Integer.parseInt(scanner.nextLine());

            // Add Patient to database
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
                String query = "INSERT INTO users (id, username, password, user_type, f_name, l_name, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, patientID);
                preparedStatement.setString(2, userName);
                preparedStatement.setString(3, pass);
                preparedStatement.setString(4, userType);
                preparedStatement.setString(5, patientFName);
                preparedStatement.setString(6, patientLName);
                preparedStatement.setInt(7, number);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Patient added successfully.");
                }
            } catch (SQLException e) {
                System.out.println("Error adding Patient: " + e.getMessage());
            }
            scanner.close();
        }
        else
        {
            System.out.println("Error creating account ");
        }
    }

    public void removePatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Patient TRN to remove: ");
        patientID = Integer.parseInt(scanner.nextLine());

        // Remove patient from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientID);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Patient removed successfully.");
            } else {
                System.out.println("Patient not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing Patient: " + e.getMessage());
        }
        scanner.close();
    }
}