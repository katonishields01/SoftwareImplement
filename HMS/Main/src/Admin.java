//Katoni Shileds(2003903)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    public void AdminMenu()
    {
        Scanner scanner = new Scanner(System.in); 
        int choice;
        int input;
        do {
            System.out.println("\t******WELCOME ADMINISTRATOR MENU******");
            System.out.println("\n1. Register Patient");
            System.out.println("2. Register Staff");
            System.out.println("3. Create Invoice");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. View Patient Personal Information Records");
            System.out.println("6. Upload Prescription Order");
            System.out.println("7. View Patient Medical History");
            System.out.println("8. Exit");
            System.out.print("Select choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //logic for registering a patient
                    System.out.println("You have chosen to Register Patient");
                    do {
                        System.out.println("\n1. Register Patient");
                        System.out.println("2. Update Patient");
                        System.out.println("3. Remove Patient");
                        System.out.println("4. Exit");
                        System.out.print("Select choice: ");
                        input = scanner.nextInt();
                        switch (input) {
                            case 1:
                                Patient p1 = new Patient();
                                p1.addPatient();
                            break;

                            case 2:
                                //code to update
                            break;

                            case 3:
                            Patient p3 = new Patient();
                            p3.removePatient();
                            break;

                            case 4:
                            System.out.println("Exiting...");
                            AdminMenu();
                            break;

                            default:
                            System.out.println("Invalid option. Please choose a number from 1 to 4.");
                                break;
                        }
                    } while (input != 4);
                    break;
                    
                case 2:
                    //logic for registering a staff 
                    System.out.println("You have chosen to Register Staff");
                    do {
                        System.out.println("\n1. Register Staff");
                        System.out.println("2. View Staff");
                        System.out.println("3. Remove Staff");
                        System.out.println("4. Exit");
                        System.out.print("Select choice: ");
                        input = scanner.nextInt();
                        switch (input) {
                            case 1:
                            
                                do {
                                    System.out.println("\n1. Doctor");
                                    System.out.println("2. Lab Tech");
                                    System.out.println("3. Nurse");
                                    System.out.println("4. Exit");
                                    System.out.print("Select choice: ");
                                    input = scanner.nextInt();
                                    switch (input) {
                                        case 1:
                                        System.out.println("\nDoctor Selected");
                                            Doctor d1 = new Doctor();
                                            d1.addDoctor();
                                            break;

                                        case 2:
                                        System.out.println("\nLab Tech Selected");
                                        LabTech r1 = new LabTech();
                                        r1.addLabTech();
                                            break;

                                        case 3:
                                        System.out.println("\nNurse Selected");
                                        Nurse n1 = new Nurse();
                                        n1.addNurse();
                                            break;

                                        case 4:
                                        System.out.println("Exiting...");
                                        AdminMenu();
                                            break;

                                        default:
                                        System.out.println("Invalid option. Please choose a number from 1 to 4.");
                                            break;
                                    }
                                }while (input != 4);
                            break;

                            case 2:
                                do {
                                    System.out.println("\n1. Doctor");
                                    System.out.println("2. Lab Tech");
                                    System.out.println("3. Nurse");
                                    System.out.println("4. Exit");
                                    System.out.print("Select choice: ");
                                    input = scanner.nextInt();
                                    switch (input) {
                                        case 1:
                                        System.out.println("\nDoctor Selected");
                                        Doctor d1 = new Doctor();
                                        d1.viewDoctorDetails();
                                            break;

                                        case 2:
                                        System.out.println("\nLab Tech Selected");
                                        LabTech r2 = new LabTech();
                                        r2.viewLabTechDetails();
                                            break;

                                        case 3:
                                        System.out.println("\nNurse Selected");
                                        Nurse n1 = new Nurse();
                                        n1.viewNurseDetails();
                                            break;

                                        case 4:
                                        System.out.println("Exiting...");
                                        AdminMenu();
                                            break;
                                    
                                        default:
                                        System.out.println("Invalid option. Please choose a number from 1 to 4.");
                                            break;
                                    }

                                }while (input != 4);
                            break;

                            case 3:
                                do {
                                    System.out.println("\n1. Doctor");
                                    System.out.println("2. Lab Tech");
                                    System.out.println("3. Nurse");
                                    System.out.println("4. Exit");
                                    System.out.print("Select choice: ");
                                    input = scanner.nextInt();
                                    switch (input) {
                                        case 1:
                                        System.out.println("\nDoctor Selected");
                                        Doctor d1 = new Doctor();
                                        d1.removeDoctor();
                                            break;

                                        case 2:
                                        System.out.println("\nLab Tech Selected");
                                        LabTech r1 = new LabTech();
                                        r1.removeLabTech();
                                            break;

                                        case 3:
                                        System.out.println("\nNurse Selected");
                                        Nurse n1 = new Nurse();
                                        n1.removeNurse(); 
                                            break;

                                        case 4:
                                        System.out.println("Exiting...");
                                        AdminMenu();
                                            break;
                                    
                                        default:
                                        System.out.println("Invalid option. Please choose a number from 1 to 4.");
                                        
                                            break;
                                    }

                                }while (input != 4);
                            break;

                            case 4:
                            System.out.println("Exiting...");
                            AdminMenu();
                            break;
                            default:
                            System.out.println("Invalid option. Please choose a number from 1 to 4.");
                                break;
                        }
                    } while (input != 4);
                    break;

                case 3:
                    //logic for creating an invoice 
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
//id=[0], testdone=[1], fn=[2],ln=[3], cost=[4]
                        connection.close();
                    } catch (SQLException e) {
                        System.out.println("Error viewing Patient Invoice " + e.getMessage());
                    }
        
                    break;

                case 4:
                    //logic for scheduling an appointment 
                    System.out.println("You have chosen to Schedule Appointment");
                    Doctor doc = new Doctor();
                    doc.DoctorMenu(); 
                    break;

                case 5:
                    //logic for viewing patient basic information records
                    System.out.println("You have chosen to View Patient Basic Information Records");
                    Patient p1 = new Patient();
                    p1.viewPatientDetails();
                    break;

                case 6:
                    // logic for uploading a prescription order 
                    System.out.println("You have chosen to Upload Prescription Order");
                    //code
                    break;

                case 7:
                    //logic for viewing patient medical history 
                    System.out.println("You have chosen to View Patient Medical History");
                    ResultsStorage re3 = new ResultsStorage();
                    re3.viewLabResults();
                    break;

                case 8:
                System.out.println("Exiting...");
                System.out.println("Logged out Successfully");
                Main in = new Main();
                in.Home();
                default:
                    System.out.println("Invalid option. Please choose a number from 1 to 8.");
                    AdminMenu();
                    break;
            }
        }while (choice != 8);
        scanner.close();
    }
}
