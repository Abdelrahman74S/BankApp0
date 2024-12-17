package login;

import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder; // Fix: Missing import for EmptyBorder
import java.awt.*;

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    public static HashMap<String, User> users = new HashMap<>(); // Changed to HashMap for efficiency

    // Method to check for unique username and register the user
    public synchronized static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        int accountId = users.size() + 1; // Generate a unique account ID
        User newUser = new User(username, password, accountId);
        users.put(username, newUser); // Add the user to the HashMap
        return true; // Registration successful
    }

    public Register() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 659, 414);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(195, 69, 66, 17);
        contentPane.add(usernameLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setBounds(260, 67, 129, 20);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(195, 141, 88, 14);
        contentPane.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(260, 138, 129, 20);
        contentPane.add(passwordField);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(93, 222, 129, 51);
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Validate input fields
            if (!username.isEmpty() && !password.isEmpty()) {
                boolean isRegistered = Register.registerUser(username, password); // Check for unique username and register user

                if (isRegistered) {
                    JOptionPane.showMessageDialog(Register.this, "Registration Successful! Your User ID is: " + (users.size()));
                    usernameField.setText(""); // Clear the fields
                    passwordField.setText("");
                } else {
                    JOptionPane.showMessageDialog(Register.this, "Username already exists. Please choose a different username.");
                }
            } else {
                JOptionPane.showMessageDialog(Register.this, "Please fill in all fields.");
            }
        });
        contentPane.add(registerButton);

        // Back to Login Button
        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(439, 222, 129, 51);
        backButton.addActionListener(e -> {
            Login loginForm = new Login();
            loginForm.setVisible(true);
            dispose(); // Close registration window
        });
        contentPane.add(backButton);
    }
}