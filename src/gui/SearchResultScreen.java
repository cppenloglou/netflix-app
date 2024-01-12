package gui;

import Controllers.FileController;
import Controllers.GuiController;
import api.Movie;
import api.Series;
import api.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchResultScreen extends JFrame {

    private JList<String> resultList;
    private DefaultListModel<String> listModel;
    private JButton addToFavoriteButton, exitButton;

    public static Movie currentMovieSelected;
    public static Series currentSeriesSelected;
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

        if(SearchForm.movies != null){
//            Show movies

            for(Movie movie : SearchForm.movies){
                listModel.addElement(movie.getTitle());
            }

        }
        else if (SearchForm.series != null){
//            Show Series
            for(Series series : SearchForm.series){
                listModel.addElement(series.getTitle());
            }
        }


        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        resultList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Handle selection change
                if (!e.getValueIsAdjusting()) {
                    // Retrieve the selected value
                    String selectedValue = resultList.getSelectedValue();

                    if( SearchForm.movies != null){
                        for(Movie movie : SearchForm.movies) {
                            if (movie.getTitle().equals(selectedValue)) {

                                if(GuiController.reviewRatingForm == null){
                                    SearchResultScreen.currentMovieSelected = movie;
                                    GuiController.showReviewRatingForm(true);

                                    GuiController.showViewContentScreen(true);
                                }
                            }
                        }
                    }else if (SearchForm.series != null){
                        for(Series series : SearchForm.series){
                            if(series.getTitle().equals(selectedValue)){
                                if(GuiController.reviewRatingForm == null){
                                    SearchResultScreen.currentSeriesSelected = series;
                                    GuiController.showReviewRatingForm(true);
                                    GuiController.showViewContentScreen(true);

                                }
                            }
                        }
                    }
                }

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

                GuiController.showLogInForm(true);

                if(GuiController.mainUser.getIsAdmin()){
                    GuiController.showSeriesRegistrationForm(false);
                    GuiController.showMovieRegistrationForm(false);
                }

                GuiController.showSearchScreen(false);
                GuiController.showSearchResultsScreen(false);

            }
        });
        add(exitButton, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private void addToFavorites() {

        int[] selectedIndices = resultList.getSelectedIndices();
        if (selectedIndices.length > 0) {
            ArrayList<String> results = new ArrayList<>();
            StringBuilder selectedResults = new StringBuilder();
            for (int index : selectedIndices) {
                String result = listModel.getElementAt(index);
                results.add(result);
                selectedResults.append(result).append("\n");
            }

            if( SearchForm.movies != null){
                for(String rs: results){
                    for(Movie movie : SearchForm.movies){
                        if(movie.getTitle().equals(rs)){
                            GuiController.mainUser.addNewFavoriteMovie(movie);
                        }
                    }
                }
            }else if (SearchForm.series != null){
                for(String rs: results){
                    for(Series series : SearchForm.series){
                        if(series.getTitle().equals(rs)){
                            GuiController.mainUser.addNewFavoriteSeries(series);
                        }
                    }
                }
            }

            // Refresh the user file with new data
            FileController.writeUsersToFile(User.UsersList);

            // Display the results
            JOptionPane.showMessageDialog(null, "Added to Favorites:\n" + selectedResults.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Please select results to add to favorites.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
