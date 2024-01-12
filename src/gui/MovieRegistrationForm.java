package gui;

import Controllers.GuiController;
import api.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieRegistrationForm extends JFrame {

    private JTextField titleField, yearField, durationField;
    private JTextArea descriptionTextArea;
    private JRadioButton over18RadioButton, no18RadioButton;
    private JComboBox<String> categoryComboBox;
    private JTextField starringTextField;

    public MovieRegistrationForm() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Movie Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Title:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        titleField = new JTextField();
        titleField.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        add(titleField, gbc);

        // Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 7;
        add(new JLabel("Description:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 7;
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        descriptionTextArea.setRows(5); // Set the number of rows
        descriptionTextArea.setColumns(30); // Set the number of columns
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        add(scrollPane, gbc);

        // Maturity
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 7;
        add(new JLabel("Maturity:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        over18RadioButton = new JRadioButton("Over 18");
        over18RadioButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        add(over18RadioButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        no18RadioButton = new JRadioButton("No");
        no18RadioButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        add(no18RadioButton, gbc);

        ButtonGroup maturityGroup = new ButtonGroup();
        maturityGroup.add(over18RadioButton);
        maturityGroup.add(no18RadioButton);

        // Year
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(new JLabel("Year:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 6;
        yearField = new JTextField();
        yearField.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        add(yearField, gbc);

        // Duration
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(new JLabel("Duration (minutes):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 6;
        durationField = new JTextField();
        durationField.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        add(durationField, gbc);

        // Category
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        add(new JLabel("Category:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 6;
        String[] categories = { "Select", "Horror", "Drama", "Sci-Fi", "Comedy", "Action" };
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        add(categoryComboBox, gbc);

        // Starring
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        add(new JLabel("Starring:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 6;
        starringTextField = new JTextField("Cillian Murphy, Emily Blunt, Matt Damon");
        starringTextField.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        add(starringTextField, gbc);

        // Submit Button
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String errorMessage = validateForm();
                if (errorMessage != null) {
                    JOptionPane.showMessageDialog(null, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Form is valid, proceed with submission
                    handleSubmission();
                }
            }
        });
        add(submitButton, gbc);

        // Exit Button
        gbc.gridx = 4;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        JButton exitButton = new JButton("Close");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.showMovieRegistrationForm(false);
            }
        });
        add(exitButton, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private String validateForm() {
        // Validate title
        if (titleField.getText().isEmpty()) {
            return "Title is required.";
        }

        // Validate description
        if (descriptionTextArea.getText().isEmpty()) {
            return "Description is required.";
        }

        // Validate maturity
        if (!over18RadioButton.isSelected() && !no18RadioButton.isSelected()) {
            return "Maturity selection is required.";
        }

        // Validate year
        if (yearField.getText().isEmpty() || !isNumeric(yearField.getText())) {
            return "Year must be a numeric value.";
        }

        // Validate duration
        if (durationField.getText().isEmpty() || !isValidDuration(durationField.getText())) {
            return "Duration must be in the format 'X', where X is a numeric value.";
        }

        // Validate category
        if (categoryComboBox.getSelectedIndex() == 0) {
            return "Category selection is required.";
        }

        return null; // Form is valid
    }

    private boolean isValidDuration(String duration) {
        return true;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void handleSubmission() {

        String title = titleField.getText();
        String description = descriptionTextArea.getText();
        boolean over18 = over18RadioButton.isSelected();
        boolean no18 = no18RadioButton.isSelected();
        String year = yearField.getText();
        String duration = durationField.getText();
        String category = (String) categoryComboBox.getSelectedItem();

        String actors = starringTextField.getText();

//Create new Movie
        GuiController.mainUser.createMovie((Admin)GuiController.mainUser, title, description, over18, category, year, duration, actors, 0.0);

        JOptionPane.showMessageDialog(null, "Form submitted successfully!", "Submission Success",
                JOptionPane.INFORMATION_MESSAGE);
        clearForm(); // Optional: Clear the form after successful submission
    }


    private void clearForm() {
        titleField.setText("");
        descriptionTextArea.setText("");
        over18RadioButton.setSelected(false);
        no18RadioButton.setSelected(false);
        yearField.setText("");
        durationField.setText("");
        categoryComboBox.setSelectedIndex(0);
    }

}
