//Chevon Sutherland (2008276)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient {
    private int patientID;
    private String patientFName;
    private String patientLName;
    private String userName;
    private String date;
    private String time;
    private String doctor;
    private String pass;
    private String userType;
    private int number;

    public void patientMenu() 
    {
        Scanner scanner = new Scanner (System.in);
        //String requestAppointment,
        String requestPrescription;
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
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice)
            {
                /* Case 1 to allow user to input preferred appointment date */
                case 1:
                System.out.print("Enter First Name: ");
                patientFName = scanner.nextLine();
        
                System.out.print("Enter Last Name: ");
                patientLName = scanner.nextLine();
        
                System.out.print("Enter TRN/ID#: ");
                patientID = Integer.parseInt(scanner.nextLine());
        
                System.out.print("Enter Doctor's Name: ");
                doctor = scanner.nextLine();

                System.out.print("Enter appointment date (YYYY-MM-DD): ");
                date = scanner.nextLine();

                System.out.print("Enter appointment time (HH:MM): ");
                time = scanner.nextLine();
        
                
                // Add appointment to database
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
                    String query = "INSERT INTO appointment (patientid, f_name, l_name, doctorname, appointment_time) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, patientID);
                    preparedStatement.setString(2, patientFName);
                    preparedStatement.setString(3, patientLName);
                    preparedStatement.setString(4, doctor);
                    preparedStatement.setString(5, date + "" + time);
        
                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Appointment added successfully.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error adding appointment: " + e.getMessage());
                }
                break;

                /* Case 2 to allow user to request for lab results */
                case 2:
                ResultsStorage re1 = new ResultsStorage();
                re1.viewLabResults();
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
                    ArrayList <String[]> resultsRecords = new ArrayList<String[]>();
                    ArrayList <String> services = new ArrayList<String>();

                    System.out.println("You have chosen to Create Invoice");
                    System.out.println("Enter Patient ID to continue: ");
                    int patientid = scanner.nextInt();
        
                    try {  
                        String url = "jdbc:mysql://localhost:3306/admin";
                        String user = "root";
                        String password = "SIProject2024";
                        Connection connection = DriverManager.getConnection(url, user, password);

                        String query = "SELECT patientid, test_done, patientf_name, patientl_name FROM results WHERE patientid = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, patientid);

                        java.sql.ResultSet resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            String[] record = {resultSet.getString("patientid"), resultSet.getString("test_done"), 
                            resultSet.getString("patientf_name"), resultSet.getString("patientl_name"), ""};
                            resultsRecords.add(record);
                        }
                       
                        for (String[] record : resultsRecords) {
                            String testdone = record[1];
                            String cost = "";

                            if(!services.contains(testdone)){
                                services.add(testdone);
                            }
                            
                            query = "SELECT cost FROM service WHERE itemname = ?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, testdone);

                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()){
                                cost = resultSet.getString("cost");
                            }
                            
                            record[4] = cost;
                        }

                        System.out.println("\tInvoice");
                        for(String test : services){
                            float amount = 0.00f;
                    
                            for(String[] record : resultsRecords){
                                if(record[1].equals(test) ){
                                    amount = amount + Float.parseFloat(record[4]);
                                }
                            }
                            System.out.println("--------------------------------");
                            System.out.println("Patient Name: " + resultsRecords.get(0)[2] + " " + resultsRecords.get(0)[3]);
                            System.out.println("Test Done: " + test.toUpperCase() + ": $" + amount);
                            System.out.println("--------------------------------");

                        }
                        connection.close();
                    } catch (SQLException e) {
                        System.out.println("Error viewing Patient Invoice " + e.getMessage());
                    }
        
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
        //scanner.close();
    }

    /* Method to handle validation and processing of appointment request */
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
                System.out.println("Full Name: " + resultSet.getString("f_name") + " " + resultSet.getString("l_name"));
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
        //scanner.close();
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
            //scanner.close();
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
        //scanner.close();
    }
}

//////////////////////IGNORE/////////////////////////////////
/*private static boolean val_Date_Time(String requestAppointment) 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try{
            LocalDateTime.parse(requestAppointment, formatter);
            return true;
        } catch (Exception e){
            return false;
        }
    }*/

     //scanner.close();
                /*System.out.println("Enter your preferred appointment date and time: ");
                requestAppointment = scanner.nextLine();
                if (val_Date_Time(requestAppointment))
                {
                    System.out.println("Appointment successfully scheduled for " + requestAppointment);
                } 
                else {
                    System.out.println("Invalid format. Please use yyyy-MM-dd HH:mm format");
                }*/