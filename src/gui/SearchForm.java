import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForm extends JFrame {

    private JTextField titleField, actorNameField, minRankingField;
    private JRadioButton movieRadioButton, seriesRadioButton, over18RadioButton, no18RadioButton;
    private JComboBox<String> genreComboBox;

    public SearchForm() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Search Form");
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

        // Type
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 7;
        add(new JLabel("Type:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        movieRadioButton = new JRadioButton("Movie");
        movieRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(movieRadioButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        seriesRadioButton = new JRadioButton("Series");
        seriesRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(seriesRadioButton, gbc);

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(movieRadioButton);
        typeGroup.add(seriesRadioButton);

        // Actor Name
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(new JLabel("Actor Name:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        actorNameField = new JTextField();
        actorNameField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(actorNameField, gbc);

        // Maturity
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 7;
        add(new JLabel("Maturity:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        over18RadioButton = new JRadioButton("Over 18");
        over18RadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(over18RadioButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        no18RadioButton = new JRadioButton("No");
        no18RadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(no18RadioButton, gbc);

        ButtonGroup maturityGroup = new ButtonGroup();
        maturityGroup.add(over18RadioButton);
        maturityGroup.add(no18RadioButton);

        // Genre
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(new JLabel("Genre:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 5;
        String[] genres = {"Select", "Horror", "Drama", "Sci-Fi", "Comedy", "Action"};
        genreComboBox = new JComboBox<>(genres);
        genreComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        add(genreComboBox, gbc);

        // Minimum Ranking
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(new JLabel("Minimum Ranking:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 5;
        minRankingField = new JTextField();
        minRankingField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(minRankingField, gbc);

        // Search Button
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 7;
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your search logic here
                performSearch();
            }
        });
        add(searchButton, gbc);

        // Exit Button
        gbc.gridx = 0;
        gbc.gridy = 9;
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

    private void performSearch() {
        // Implement your search logic here
        // You can access the entered values using titleField.getText(), actorNameField.getText(), etc.
        // Perform the search based on the entered criteria
        // Display the search results or take appropriate action
        JOptionPane.showMessageDialog(null, "Performing search...", "Search Info", JOptionPane.INFORMATION_MESSAGE);
    }

    
}
