package gui;

import Controllers.GuiController;
import api.Admin;
import api.Season;

import javax.swing.*;
import java.awt.*;

public class SeriesRegistrationForm extends JFrame {

    private JTextField titleField, starringTextField, seasonField, yearField;
    private JTextArea descriptionTextArea;
    private JRadioButton over18RadioButton, no18RadioButton;
    private JComboBox<String> categoryComboBox, episodeComboBox;

    public SeriesRegistrationForm() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Series Registration Form");
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
        titleField.setFont(new Font("Arial", Font.PLAIN, 18));
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
        descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionTextArea.setColumns(30);
        descriptionTextArea.setRows(5);
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
        over18RadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(over18RadioButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        no18RadioButton = new JRadioButton("No");
        no18RadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(no18RadioButton, gbc);

        ButtonGroup maturityGroup = new ButtonGroup();
        maturityGroup.add(over18RadioButton);
        maturityGroup.add(no18RadioButton);

        // Category
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(new JLabel("Category:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 6;
        String[] categories = {"Select", "Horror", "Drama", "Sci-Fi", "Comedy", "Action"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        add(categoryComboBox, gbc);

        // Starring
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(new JLabel("Starring:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 6;
        starringTextField = new JTextField("Main Actor, Actress, Supporting Cast");
        starringTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(starringTextField, gbc);

        // Season Panel
        JPanel seasonPanel = new JPanel(new GridBagLayout());
        seasonPanel.setBorder(BorderFactory.createTitledBorder("Season"));

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 7;
        add(seasonPanel, gbc);

        // Season Number
        seasonField = new JTextField();
        seasonField.setFont(new Font("Arial", Font.PLAIN, 18));
        seasonField.setColumns(15);
        GridBagConstraints seasonGbc = new GridBagConstraints();
        seasonGbc.gridx = 0;
        seasonGbc.gridy = 0;
        seasonGbc.insets = new Insets(5, 5, 5, 5);
        seasonPanel.add(new JLabel("Season Number:"), seasonGbc);
        seasonGbc.gridx = 1;
        seasonGbc.gridy = 0;
        seasonPanel.add(seasonField, seasonGbc);

        // Year
        yearField = new JTextField();
        yearField.setFont(new Font("Arial", Font.PLAIN, 18));
        yearField.setColumns(15);
        seasonGbc.gridx = 0;
        seasonGbc.gridy = 1;
        seasonPanel.add(new JLabel("Year:"), seasonGbc);
        seasonGbc.gridx = 1;
        seasonGbc.gridy = 1;
        seasonPanel.add(yearField, seasonGbc);

        // Episode
        episodeComboBox = new JComboBox<>(new String[]{"Select", "Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5"});
        episodeComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        seasonGbc.gridx = 0;
        seasonGbc.gridy = 2;
        seasonPanel.add(new JLabel("Episode:"), seasonGbc);
        seasonGbc.gridx = 1;
        seasonGbc.gridy = 2;
        seasonPanel.add(episodeComboBox, seasonGbc);



        // Mini Submit Button for Episode
//        JButton miniSubmitButton = new JButton("Mini Submit");
//        miniSubmitButton.setFont(new Font("Arial", Font.PLAIN, 14));
//        miniSubmitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String errorMessage = validateEpisodeForm();
//                if (errorMessage != null) {
//                    JOptionPane.showMessageDialog(null, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
//                } else {
//                    handleEpisodeSubmission();
//                }
//            }
//        });
//        seasonGbc.gridx = 1;
//        seasonGbc.gridy = 4;
//        seasonPanel.add(miniSubmitButton, seasonGbc);

        // Submit Button
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        submitButton.addActionListener(e -> {
            String errorMessage = validateForm();
            if (errorMessage != null) {
                JOptionPane.showMessageDialog(null, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
            } else {
                handleSubmission();
            }
        });
        add(submitButton, gbc);

        // Exit Button
        gbc.gridx = 4;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        JButton exitButton = new JButton("Close");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.addActionListener(e -> GuiController.showSeriesRegistrationForm(false));
        add(exitButton, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private String validateForm() {
        if (titleField.getText().isEmpty()) {
            return "Title is required.";
        }

        if (descriptionTextArea.getText().isEmpty()) {
            return "Description is required.";
        }

        if (!over18RadioButton.isSelected() && !no18RadioButton.isSelected()) {
            return "Maturity selection is required.";
        }

        if (categoryComboBox.getSelectedIndex() == 0) {
            return "Category selection is required.";
        }

        if (starringTextField.getText().isEmpty()) {
            return "Starring is required.";
        }

        if (seasonField.getText().isEmpty()) {
            return "Season is required.";
        }

        if (yearField.getText().isEmpty() || !isNumeric(yearField.getText())) {
            return "Year must be a numeric value.";
        }

        if (episodeComboBox.getSelectedIndex() == 0) {
            return "Episode selection is required.";
        }


        return null; // Form is valid
    }
//    private String validateEpisodeForm() {
//        // Validate the episode-specific fields
//        if (episodeComboBox.getSelectedIndex() == 0) {
//            return "Episode selection is required.";
//        }
//
//
//        return null; // Episode form is valid
//    }

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
        int year = Integer.parseInt(yearField.getText());
        String category = (String) categoryComboBox.getSelectedItem();
        String actors = starringTextField.getText();
        // Create new series
        GuiController.mainUser.createSeries((Admin)GuiController.mainUser, title,  description,  over18, category,  actors,  0.0,new Season(seasonField.getText(),categoryComboBox.getSelectedIndex() + 1,year));


        // Αυτη ειναι η συναρτηση αποθηκευσης σειρας, αναλογα με το πως θα αποθηκευουμε τα δεδομενα
        JOptionPane.showMessageDialog(null, "Series submitted successfully!", "Submission Success", JOptionPane.INFORMATION_MESSAGE);
        clearForm();
    }

//    private void handleEpisodeSubmission() {
//        // Αυτη ειναι η συναρτηση αποθηκευσης για το επισοδειο
//        JOptionPane.showMessageDialog(null, "Episode submitted successfully!", "Submission Success", JOptionPane.INFORMATION_MESSAGE);
//    }

    private void clearForm() {
        titleField.setText("");
        descriptionTextArea.setText("");
        over18RadioButton.setSelected(false);
        no18RadioButton.setSelected(false);
        categoryComboBox.setSelectedIndex(0);
        starringTextField.setText("Main Actor, Actress, Supporting Cast");
        seasonField.setText("");
        yearField.setText("");
        episodeComboBox.setSelectedIndex(0);
    }
}
