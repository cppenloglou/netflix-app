import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationForm extends JFrame {

    private JTextField nameField, middleNameField, usernameField;
    private JPasswordField passwordField;

    public UserRegistrationForm() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("User Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nameField = new JTextField();
        nameField.setColumns(25);
        add(nameField, gbc);

        // Middle Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Middle Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        middleNameField = new JTextField();
        middleNameField.setColumns(25);
        add(middleNameField, gbc);

        // Username Label and Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        usernameField = new JTextField();
        usernameField.setColumns(25);
        add(usernameField, gbc);

        // Password Label and Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        passwordField = new JPasswordField();
        passwordField.setColumns(25);
        add(passwordField, gbc);

        // Submit Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });
        add(submitButton, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private void submitForm() {
        String name = nameField.getText();
        String middleName = middleNameField.getText();
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (name.isEmpty() || middleName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // You can implement your registration logic here
            // For example, display or store the registered user data
            String message = String.format("Registration successful!\nName: %s %s\nUsername: %s\nPassword: %s",
                    name, middleName, username, password);

            JOptionPane.showMessageDialog(null, message, "Registration Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear the form after submission
            clearForm();
        }
    }

    private void clearForm() {
        nameField.setText("");
        middleNameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
    }

}
