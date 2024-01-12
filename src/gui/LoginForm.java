package gui;

import Controllers.GuiController;
import api.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                // Call the login method
                User user = loginUser(username, password);

                if (user == null){
                    GuiController.showUserRegistrationForm(true);
                }else{
                    GuiController.mainUser = user;

                    if(user.getIsAdmin()){
                        GuiController.showMovieRegistrationForm(true);
                        GuiController.showSeriesRegistrationForm(true);
                    }
                    GuiController.showSearchScreen(true);

                }
                GuiController.showLogInForm(false);

            }
        });
        panel.add(loginButton, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }


    public User loginUser(String enteredUsername, char[] enteredPassword) {
        String password = new String(enteredPassword);
        clearForm();

        return User.findUser(enteredUsername,password);
    }

    public void clearForm(){
        this.usernameField.setText("");
        this.passwordField.setText("");
    }
}
