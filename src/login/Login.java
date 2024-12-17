// Login.java
package login;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import balance.BankOperationsPage;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setTitle("Login");
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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(93, 222, 129, 51);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = Register.users.get(username);
            if (user != null && user.getPassword().equals(user.hashPassword(password))) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                BankOperationsPage operationsPage = new BankOperationsPage(user.getBankAccount());
                operationsPage.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });
        contentPane.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(439, 222, 129, 51);
        registerButton.addActionListener(e -> {
            Register registerForm = new Register();
            registerForm.setVisible(true); // Fixed: Use setVisible(true) instead of undefined displayRegisterForm
            dispose();
        });
        contentPane.add(registerButton);
    }
}
