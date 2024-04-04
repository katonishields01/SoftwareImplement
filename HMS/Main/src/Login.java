//Team Effort

//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private static final String url = "jdbc:mysql://localhost:3306/admin";
    private static final String user = "root";
    private static final String password = "SIProject2024";

    Scanner scanner = new Scanner(System.in);
    
    public void User() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String passwordInput = scanner.nextLine();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String userType = resultSet.getString("user_type");
                String hashedPassword = resultSet.getString("password");
                
                

                //if (isPasswordValid(passwordInput, hashedPassword)) {
                    if (passwordInput.equals(hashedPassword)) {
                    System.out.println("Login successful. Welcome " + userType);

                    switch (userType) {
                        case "admin":
                            Admin enter1 = new Admin();
                            enter1.AdminMenu();
                            break;

                        case "patient":
                            Patient enter2 = new Patient();
                            enter2.patientMenu();
                            break;

                        case "doctor":
                            /*Doctor enter3 = new Doctor();
                            enter3.doctorMenu();
                            break;*/

                        case "nurse":
                            Nurse enter4 = new Nurse();
                            enter4.nurseMenu();
                            break;

                        case "lab_tech":
                            LabTech enter5 = new LabTech();
                            enter5.labTechMenu();
                            break;

                        default:
                            System.out.println("Invalid user type");
                            Main in = new Main();
                            in.Home();
                    }
                } else {
                    System.out.println("Invalid username or password");
                    Main in = new Main();
                    in.Home();
                }
            } else {
                System.out.println("Invalid username or password");
                Main in = new Main();
                in.Home();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /* 
    private static boolean isPasswordValid(String passwordInput, String hashedPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedInput = md.digest(passwordInput.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : hashedInput) {
                sb.append(String.format("%02x", b));
            }
            String hashedInputString = sb.toString();

            return hashedInputString.equals(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }*/
    
}
