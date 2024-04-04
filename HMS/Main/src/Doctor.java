//Kaisia Fagan (ID#)
import java.util.Scanner;

public class Doctor {
    public void doctorMenu () {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            
            boolean consultationDocumented = false;
            String patientId = "";
            String requestStatus = "";
            String labResults = "";
                    System.out.println("Menu:");
                    System.out.println("1: Request consultation with doctor");
                    System.out.println("2: Request a prescription refill");
                    System.out.println("3: Request a chat with a doctor");
                    System.out.println("4: Request lab results");
                    System.out.println("5: Exit");
                    System.out.print("Enter your choice: ");
                    input = scanner.nextInt();
        
                    switch (input) {
                        case 1:
                            System.out.println("Request consultation with doctor");
                    
                            /*requestStatus = "approved";
                            if (requestStatus.equals("approved")) {
                                System.out.println("Start consultation");
                                // Document consultation information
                                consultationDocumented = true;
                            } else {
                                System.out.println("Consultation request not approved");
                            }*/
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
                    scanner.close();
                } while(input != 5);
            }
    }

