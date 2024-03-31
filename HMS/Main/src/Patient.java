import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Patient {
public static void main(String[] args) 
    {
        Scanner scanner = new Scanner (System.in);
        String Patient_name;
        String requestAppointment, requestLabResults, requestPrescription;
        float invoice_Bal;
        int choice;

        System.out.println("Welcome to our Hospital Management Patient System!");

        System.out.println("Please enter your name: ");
        Patient_name = scanner.nextLine();

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
                System.out.println("Enter the test ID for the lab results: ");
                requestLabResults = scanner.nextLine();
                if (validateTestID(requestLabResults))
                {
                    String labResultData = getLabResults(requestLabResults);
                    if (!labResultData.isEmpty()) 
                    {
                        System.out.println("Lab results for Test Id" + requestLabResults + ":");
                        System.out.println(labResultData);
                    }
                    else {
                        System.out.println("No results found for Test ID" +requestLabResults);
                    }
                }
                    else {
                        System.out.println("Invalid Test ID");
                    }
                break; 
                
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
                    System.out.println("Thank you for using our Patient System " + Patient_name + ".");
                    break;
                /* Defaults in the case user enters invalid choice */
                default:
                System.out.println("Invalid Choice. Please select a valid choice.");
            }
        }
        while (choice != 5);
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
    
    /*Implementation method to output the name and description */
    private static String getLabResults(String requestLabResults) 
    {
    	String query = "SELECT Name, Description FROM ValidID WHERE User_ID = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/result", "root", "suth0802");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, requestLabResults);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    return "Name: " + name + ", Description: " + description;
                } else {
                    return "No results found for Test ID " + requestLabResults;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
            return "Error getting the lab results";
        }
    }

    /*Implementation to validate the id of the patient */
    private static boolean validateTestID(String requestLabResults) 
    {
        String query = "SELECT * FROM ValidID WHERE User_ID = ?";
        boolean isValid = false;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/result", "root", "suth0802");
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, requestLabResults);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            isValid = resultSet.next(); // If there's a next row, the ID is valid
        }
        } catch (SQLException e) {
            System.err.println("Error" + e.getMessage());
        }
    return isValid;
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
}