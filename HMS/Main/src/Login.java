
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private static final String url = "jdbc:mysql://localhost:3306/";
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
            String query = "SELECT * FROM tblUsers WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                String userType = resultSet.getString("user_type");

                if (isPasswordValid(passwordInput, hashedPassword)) {
                    System.out.println("Login successful. Welcome " + userType);

                    switch (userType) {
                        case "admin":
                            Admin enter = new Admin();
                            enter.AdminMenu();
                            break;
                        case "patient":
                            // code for patient
                            break;
                        case "doctor":
                            // code for doctor
                            break;
                        case "nurse":
                            // code for nurse
                            break;
                        case "lab tech":
                            // code for lab tech
                            break;
                        default:
                            System.out.println("Invalid user type");
                    }
                } else {
                    System.out.println("Invalid username or password");
                }
            } else {
                System.out.println("Invalid username or password");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
        
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
    }
    
}
