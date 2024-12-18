package login;

import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder; 
import java.awt.*;

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    public static HashMap<String, User> users = new HashMap<>(); 

    public synchronized static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; 
        }
        int accountId = users.size() + 1; 
        User newUser = new User(username, password, accountId);
        users.put(username, newUser); 
        return true; 
    }

    public Register() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 659, 414);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(195, 69, 66, 17);
        contentPane.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(260, 67, 129, 20);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(195, 141, 88, 14);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(260, 138, 129, 20);
        contentPane.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(93, 222, 129, 51);
        
		registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                boolean isRegistered = Register.registerUser(username, password); 

                if (isRegistered) {
                    JOptionPane.showMessageDialog(Register.this,
                            "Registration Successful! Your User ID is: " + (users.size()));
                    usernameField.setText(""); 
                    passwordField.setText("");
                } else {
                    JOptionPane.showMessageDialog(Register.this,
                            "Username already exists. Please choose a different username.");
                }
            } else {
                JOptionPane.showMessageDialog(Register.this,
                        "Please fill in all fields.");
            }
		});
		
		contentPane.add(registerButton);

		JButton backButton = new JButton("Back to Login");
		backButton.setBounds(439, 222, 129, 51);
		backButton.addActionListener(e -> {
			Login loginForm = new Login();
			loginForm.setVisible(true); 
			dispose(); 
		});
		contentPane.add(backButton);
    }
}
