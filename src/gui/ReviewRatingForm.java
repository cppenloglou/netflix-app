import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewRatingForm extends JFrame {

    private JTextArea commentTextArea;
    private JRadioButton rating1, rating2, rating3, rating4, rating5;
    private ButtonGroup ratingGroup;

    public ReviewRatingForm() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Review and Rating Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 7;
        add(new JLabel("Movie/Series Review and Rating"), gbc);

        // Comment
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(new JLabel("Comment (up to 500 characters):"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        commentTextArea = new JTextArea();
        commentTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        commentTextArea.setLineWrap(true);
        commentTextArea.setColumns(25);
        commentTextArea.setRows(6);
        JScrollPane scrollPane = new JScrollPane(commentTextArea);
        add(scrollPane, gbc);

        // Rating
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 7;
        add(new JLabel("Rating (1 to 5):"), gbc);

        // Radio Buttons
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rating1 = new JRadioButton("1");
        rating1.setFont(new Font("Arial", Font.PLAIN, 18));
        rating2 = new JRadioButton("2");
        rating2.setFont(new Font("Arial", Font.PLAIN, 18));
        rating3 = new JRadioButton("3");
        rating3.setFont(new Font("Arial", Font.PLAIN, 18));
        rating4 = new JRadioButton("4");
        rating4.setFont(new Font("Arial", Font.PLAIN, 18));
        rating5 = new JRadioButton("5");
        rating5.setFont(new Font("Arial", Font.PLAIN, 18));

        ButtonGroup ratingGroup = new ButtonGroup();
        ratingGroup.add(rating1);
        ratingGroup.add(rating2);
        ratingGroup.add(rating3);
        ratingGroup.add(rating4);
        ratingGroup.add(rating5);

        ratingPanel.add(rating1);
        ratingPanel.add(rating2);
        ratingPanel.add(rating3);
        ratingPanel.add(rating4);
        ratingPanel.add(rating5);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 7;
        add(ratingPanel, gbc);

        // Submit Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 7;
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String errorMessage = validateForm();
                if (errorMessage != null) {
                    JOptionPane.showMessageDialog(null, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    handleSubmission();
                }
            }
        });
        add(submitButton, gbc);

        // Exit Button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 7;
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private String validateForm() {
        // Validate the comment field
        String comment = commentTextArea.getText();
        if (comment.isEmpty()) {
            return "Comment is required.";
        }
        if (comment.length() > 500) {
            return "Comment must be up to 500 characters.";
        }

        // Validate the rating field
        if (!rating1.isSelected() && !rating2.isSelected() && !rating3.isSelected() &&
                !rating4.isSelected() && !rating5.isSelected()) {
            return "Rating selection is required.";
        }

        return null; // Form is valid
    }

    private void handleSubmission() {
        // Submission logic
        int selectedRating = getSelectedRating();
        String comment = commentTextArea.getText();

        // Display or store the submitted data
        JOptionPane.showMessageDialog(null,
                "Review submitted successfully!\nRating: " + selectedRating + "\nComment: " + comment,
                "Submission Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear the form
        clearForm();
    }

    private int getSelectedRating() {
        if (rating1.isSelected()) return 1;
        if (rating2.isSelected()) return 2;
        if (rating3.isSelected()) return 3;
        if (rating4.isSelected()) return 4;
        if (rating5.isSelected()) return 5;
        return -1; // No rating selected (should not happen with proper validation)
    }

    private void clearForm() {
        commentTextArea.setText("");
        ratingGroup.clearSelection();
    }

}
