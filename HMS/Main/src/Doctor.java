//Kaisia Fagan (ID#)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    private int doctorID;
    private String doctorFName;
    private String doctorLName;
    private String userName;
    private String pass;
    private String userType;
    private int number;
    private int input;
    private int patientId;
    private String doctorName;
    private String symptoms;
    private String diagnosis;
    private String prescription;
    private String consultationDate;
    private String consultationTime;

    public void DoctorMenu () {
        
        Scanner scanner = new Scanner(System.in);
       

        do {
            System.out.println("Doctor's Menu:");
            System.out.println("1: Document consultation");
            System.out.println("2: Document prescription");
            System.out.println("3: Records Symptoms and Diagnosis");
            System.out.println("4: Request lab results");
            System.out.println("5: Exit");
            System.out.print("Enter your choice: ");
            input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    System.out.println("Document consultation");
                        System.out.println("Enter Doctor's name: ");
                        doctorName = scanner.nextLine();
            
                        System.out.println("Enter consultation date (yyyy-mm-dd): ");
                        consultationDate = scanner.nextLine();
            
                        System.out.println("Enter consultation time (hh:mm): ");
                        consultationTime = scanner.nextLine();
            
                        System.out.println("Enter Patient ID: ");
                        patientId = Integer.parseInt(scanner.nextLine());

                        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
                            String query = "INSERT INTO results (patientid, doctorName, consult) VALUES (?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, patientId);
                            preparedStatement.setString(2, doctorName);
                            preparedStatement.setString(3, consultationDate + " " + consultationTime);
                
                            int rowsInserted = preparedStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("Consultation added successfully.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error adding Consultation: " + e.getMessage());
                        }
                    break;

                case 2:
                        System.out.println("Document a Prescription");
                        System.out.print("Enter PatientID: ");
                        patientId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Document your prescribed medications: ");
                        prescription = scanner.nextLine();
                        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
                            String query = "INSERT INTO results (patientid, prescription) VALUES (?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, patientId);
                            preparedStatement.setString(2, prescription);
                            
                
                            int rowsInserted = preparedStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("Symptoms and Diagnosis added successfully.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error adding Symptoms and Diagnosis: " + e.getMessage());
                        }
                    break;

                case 3:
                    System.out.println("Document Symptoms and Diagnosis");
                    System.out.println("Enter Patient TRN: ");
                    patientId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Patient Symptoms: ");
                    symptoms = scanner.nextLine();

                    System.out.print("Record your diagnosis: ");
                    diagnosis = scanner.nextLine();
                    
                        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
                            String query = "INSERT INTO results (patientid, symptom, diagnosis) VALUES (?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, patientId);
                            preparedStatement.setString(2, symptoms);
                            preparedStatement.setString(3, diagnosis);
                
                            int rowsInserted = preparedStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("Symptoms and Diagnosis added successfully.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error adding Symptoms and Diagnosis: " + e.getMessage());
                        }
                    break;

                case 4:
                    System.out.println("Request Lab Results");
                    ResultsStorage re3 = new ResultsStorage();
                    re3.viewLabResults();
                    break;
                case 5:
                System.out.println("Exiting...");
                System.out.println("Logged out Successfully");
                Main in = new Main();
                in.Home();
                default:
                    System.out.println("Invalid! Please enter a valid input");
                    break;
            }
        }while (input != 5);
        scanner.close();
    }



    public void addDoctor() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Doctor's ID: ");
        doctorID = Integer.parseInt(scanner.nextLine());
       
        System.out.print("Enter User Type: ");
        userType = scanner.nextLine();

        System.out.print("Enter Doctor's First Name: ");
        doctorFName = scanner.nextLine();

        System.out.print("Enter Doctor's Last Name: ");
        doctorLName = scanner.nextLine();

        System.out.print("Enter Doctor's Username: ");
        userName = scanner.nextLine();

        System.out.print("Enter Doctor's Password: ");
        pass = scanner.nextLine();

        System.out.print("Enter Doctor's Contact: ");
        number = Integer.parseInt(scanner.nextLine());

        // Add doctor to database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "INSERT INTO users (id, username, password, user_type, f_name, l_name, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorID);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, pass);
            preparedStatement.setString(4, userType);
            preparedStatement.setString(5, doctorFName);
            preparedStatement.setString(6, doctorLName);
            preparedStatement.setInt(7, number);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Doctor added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
        scanner.close();
    }

    public void removeDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Doctor's ID to remove: ");
        doctorID = Integer.parseInt(scanner.nextLine());
        

        // Remove doctor from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorID);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Doctor removed successfully.");
            } else {
                System.out.println("Doctor not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing doctor: " + e.getMessage());
        }
        scanner.close();
    }

    public void viewDoctorDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Doctor's ID to view details: ");
        int doctorID = Integer.parseInt(scanner.nextLine());

        // View doctor details from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "SIProject2024")) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorID);

            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Doctor Details:");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Full Name: " + resultSet.getString("f_name") + resultSet.getString("l_name"));
                System.out.println("Username " + resultSet.getString("username"));
                System.out.println("Password: " + resultSet.getString("password"));
                System.out.println("User Type: " + resultSet.getString("user_type"));
                System.out.println("Contact: " + resultSet.getInt("contact"));
            } else {
                System.out.println("Doctor not found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing Doctor details: " + e.getMessage());
        }
        scanner.close();
    }
}


/////////////////////////////////////////////////////////////
/*
 * import java.util.Scanner;

public class Doctor {
        Scanner scanner = new Scanner(System.in);
        String doctorName = "";
        String patientID = "";
        String symptoms = "";
        String diagnosis = "";
        String prescription = "";
        String consultationDate = "";
        String consultationTime = "";

        System.out.println("Enter 1 to start documenting a consultation");

        int input = scanner.nextInt();

        if (input == 1) {
            System.out.println("Consultation Documentation");
		
        System.out.print("Enter Doctor's name: ");
        doctorName = scanner.nextLine();

            System.out.print("Enter consultation date (yyyy-mm-dd): ");
            consultationDate = scanner.next();

            System.out.print("Enter consultation time (hh:mm): ");
            consultationTime = scanner.next();

            System.out.print("Enter Patient ID: ");
            patientID = scanner.next();
System.out.print("Enter Patient Symptoms: ");
            scanner.nextLine(); // consume newline character
            symptoms = scanner.nextLine();

            System.out.print("Record your diagnosis: ");
            diagnosis = scanner.nextLine();

            System.out.print("Document your prescribed medications: ");
            prescription = scanner.nextLine();
        } else {
            // Invalid input
            System.out.println("Invalid! Please enter a valid input");
        }

        System.out.println("-----------------------------------");
        System.out.println("Consultation Documentation");
        System.out.println("Doctor: " + doctorName);
        System.out.println("Date: " + consultationDate);
        System.out.println("Time: " + consultationTime);
        System.out.println("Patient ID: " + patientID);
        System.out.println("Symptoms: " + symptoms);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Prescription: " + prescription);
        System.out.println("-----------------------------------");

        scanner.close();
    }
 * 
 * 
 * 
 * 
 * 
 * 
 */

























/////////////////////////////ignore/////////////////////////
/* 
public class Doctor {
    public void DoctorMenu () {
    
        Scanner scanner = new Scanner(System.in);
        int input;
        boolean consultationDocumented = false;
        String patientId = "";
        String requestStatus = "";
        String labResults = "";

        while (true) {
            System.out.println("Menu:");
            System.out.println("1: Request consultation with doctor");
            System.out.println("2: Request a prescription refill");
            System.out.println("3: Request a chat with a doctor");
            System.out.println("4: Request lab results");
            System.out.println("5: Exit");
            System.out.print("Enter your choice: ");
            int input = scanner.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Request consultation with doctor");
                    // Request consultation with doctor
                    requestStatus = "approved";
                    if (requestStatus.equals("approved")) {
                        System.out.println("Start consultation");
                        // Document consultation information
                        consultationDocumented = true;
                    } else {
                        System.out.println("Consultation request not approved");
                    }
                    break;
                case 2:
                    if (consultationDocumented) {
                        System.out.println("Request a Prescription Refill");
                        System.out.print("Enter PatientID: ");
                        patientId = scanner.next();
                        System.out.println("Patient ID: " + patientId);
                    } else {
                        System.out.println("Consultation not yet documented");
                    }
                    break;
                case 3:
                    System.out.println("Request Chat with a doctor");
                    System.out.print("Enter PatientID: ");
                    patientId = scanner.next();
                    System.out.println("Patient ID: " + patientId);
                    // Chat with patient
                    break;
                case 4:
                    System.out.println("Request Lab Results");
                    System.out.print("Enter PatientID: ");
                    patientId = scanner.next();
                    // Request Lab Results
                    if (labResults.equals("available")) {
                        System.out.println("Upload patient lab results");
                        // Upload patient lab results
                        System.out.println("Lab results have been uploaded");
                        // Notify patient of available results
                    } else {
                        System.out.println("Lab results not available");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    Login in = new Login();
                    in.User();
                default:
                    System.out.println("Invalid! Please enter a valid input");
                    break;
            }
        }
    }
}
}*/
