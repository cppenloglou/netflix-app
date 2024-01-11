import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SearchResultScreen extends JFrame {

    private JList<String> resultList;
    private DefaultListModel<String> listModel;
    private JButton addToFavoriteButton, exitButton;

    public SearchResultScreen() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Search Results");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Search Result List
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 4;

        listModel = new DefaultListModel<>();
        listModel.addElement("Result 1");
        listModel.addElement("Result 2");
        listModel.addElement("Result 3");
        listModel.addElement("Result 4");

        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        resultList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Handle selection change if needed
            }
        });

        JScrollPane scrollPane = new JScrollPane(resultList);
        add(scrollPane, gbc);

        // Add to Favorite Button
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        addToFavoriteButton = new JButton("Add to Favorites");
        addToFavoriteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addToFavoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToFavorites();
            }
        });
        add(addToFavoriteButton, gbc);

        // Exit Button
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        exitButton = new JButton("Exit");
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

    private void addToFavorites() {
        int[] selectedIndices = resultList.getSelectedIndices();
        if (selectedIndices.length > 0) {
            StringBuilder selectedResults = new StringBuilder();
            for (int index : selectedIndices) {
                String result = listModel.getElementAt(index);
                selectedResults.append(result).append("\n");
            }
            // Add the selected results to favorites (you can implement your logic here)
            JOptionPane.showMessageDialog(null, "Added to Favorites:\n" + selectedResults.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Please select results to add to favorites.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
