import java.util.Scanner;

public class Main{
public static void main(String[] args) {
    Main in = new Main();
        in.Home();
}

public void Home() {
    Scanner scanner = new Scanner(System.in); 
    int choice;
    int input;
    System.out.print("Please Select: ");
    System.out.println("\n1. Log-In");
    System.out.println("2. Sign-Up");
    choice = scanner.nextInt();
    do {
    switch (choice) {
        case 1:
            System.out.println("Log-In Selected");
            Login user = new Login();
            user.User();
            break;
        case 2:
            System.out.println("Creating New Account");
            // Add New Account
            do {
                System.out.print("Please Select: ");
                System.out.println("\n1. Patient");
                System.out.println("2. Staff");
                System.out.println("3. Exit");
                input = scanner.nextInt();
                switch (input) {
                    case 1:
                        System.out.println("Creating New Patient Account");
                        Patient p1 = new Patient();
                        p1.addPatient();
                        System.out.println("Account Created Successfully");  
                    break;

                    case 2:
                        System.out.println("Please Log Admin or Contact Admin to Continue.");
                        Main in2 = new Main();
                        in2.Home();   
                    break;

                    case 3:
                        System.out.println("Exiting...");
                        Main in3 = new Main();
                        in3.Home();
                    break;

                    default:
                        System.out.println("Invalid option. Please choose a number from 1 to 3.");
                        Main in4 = new Main();
                        in4.Home();
                    break;
            }
                choice = scanner.nextInt();
            }while(true && input != 3);
            break;
            default:
            System.out.println("Invalid option. Please choose a number from 1 to 3.");
            Main in5 = new Main();
            in5.Home();
            break;
    }
    
    }while (true && choice != 2);
    scanner.close();
}
                
}







////////////////////////IGNORE/////////////////////////////////////////////////
/*import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main {
    private JFrame frame;
    private JButton logInButton;
    private JButton signUpButton;
    private JLabel messageLabel;
    private Scanner scanner;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main window = new Main();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Main Menu");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        logInButton = new JButton("Log-In");
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Log-In Selected");
                Login user = new Login();
                user.User();
            }
        });

        signUpButton = new JButton("Sign-Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Creating New Account");
                scanner = new Scanner(System.in);
                int input;
                do {
                    System.out.print("Please Select: ");
                    System.out.println("\n1. Patient");
                    System.out.println("2. Staff");
                    System.out.println("3. Exit");
                    input = scanner.nextInt();
                    switch (input) {
                        case 1:
                            System.out.println("Creating New Patient Account");
                            Patient p1 = new Patient();
                            p1.addPatient();
                            System.out.println("Account Created Successfully");
                            break;
                        case 2:
                            System.out.println("Please Log Admin or Contact Admin to Continue.");
                            // Main in2 = new Main();
                            // in2.Home();
                            break;
                        case 3:
                            System.out.println("Exiting...");
                            // Main in3 = new Main();
                            // in3.Home();
                            break;
                        default:
                            System.out.println("Invalid option. Please choose a number from 1 to 3.");
                            // Main in4 = new Main();
                            // in4.Home();
                            break;
                    }
                } while (input != 3);
                scanner.close();
            }
        });

        messageLabel = new JLabel("Select an option:");

        frame.getContentPane().add(logInButton);
        frame.getContentPane().add(signUpButton);
        frame.getContentPane().add(messageLabel);
    }
}*/
/*public class Main {
        private JFrame frame;
        private JButton logInButton;
        private JButton signUpButton;
        private JLabel messageLabel;
    public static void main(String[] args) throws Exception {
        /*EventQueue.invokeLater(() -> {
            try {
                Main window = new Main();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
        /*Main in = new Main();
        in.Home();
    }
    public void Home() {
            frame = new JFrame("Main Menu");
            frame.setBounds(100, 100, 450, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(new FlowLayout());
                logInButton = new JButton("Log-In");
            logInButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    messageLabel.setText("Log-In Selected");
                    Login user = new Login();
                        user.User();
                }
            });

            signUpButton = new JButton("Sign-Up");
            signUpButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    messageLabel.setText("Creating New Account");
                    // Add code to handle sign-up
                    Scanner scanner = new Scanner(System.in); 
                    //int choice;
                    int input;
                    do {
                        System.out.print("Please Select: ");
                        System.out.println("\n1. Patient");
                        System.out.println("2. Staff");
                        System.out.println("3. Exit");
                        input = scanner.nextInt();
                        switch (input) {
                        case 1:
                        System.out.println("Creating New Patient Account");
                        Patient p1 = new Patient();
                        p1.addPatient();
                        System.out.println("Account Created Successfully");  
                        break;
                        case 2:
                        System.out.println("Please Log Admin or Contact Admin to Continue.");
                        Main in2 = new Main();
                        in2.Home();   
                        break;
                        case 3:
                        System.out.println("Exiting...");
                        Main in3 = new Main();
                        in3.Home();
                        break;
                        default:
                        System.out.println("Invalid option. Please choose a number from 1 to 3.");
                        Main in4 = new Main();
                        in4.Home();
                        break;
                    }
                        //choice = scanner.nextInt();
                    }while(true && input != 3);
            }
        });

        messageLabel = new JLabel("Select an option:");

        frame.getContentPane().add(logInButton);
        frame.getContentPane().add(signUpButton);
        frame.getContentPane().add(messageLabel);
    }
    }*/


                
 

  
   
    
    /*public class Main {
        
    
        public static void main(String[] args) {
            EventQueue.invokeLater(() -> {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    
        public Main() {
            initialize();
        }
    
        private void initialize() {
            frame = new JFrame("Main Menu");
            frame.setBounds(100, 100, 450, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(new FlowLayout());
    
            logInButton = new JButton("Log-In");
            logInButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    messageLabel.setText("Log-In Selected");
                    // Add code to handle log-in
                }
            });
    
            signUpButton = new JButton("Sign-Up");
            signUpButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    messageLabel.setText("Creating New Account");
                    // Add code to handle sign-up
                }
            });
    
            messageLabel = new JLabel("Select an option:");
    
            frame.getContentPane().add(logInButton);
            frame.getContentPane().add(signUpButton);
            frame.getContentPane().add(messageLabel);
        }
    }*/