package gui;

import Controllers.GuiController;
import api.Review;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ViewContentScreen extends JFrame {

    private JTextArea contentArea;
    private JTextArea reviewsArea;
    private JButton submitReviewButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton moviesButton;
    private JButton seriesButton;

    private boolean isAdmin; // Set to true for administrator, false for a simple viewer

    public ViewContentScreen(boolean isAdmin) {
        this.isAdmin = isAdmin;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("View Content Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        if(SearchResultScreen.currentMovieSelected != null){
            // Button to switch to Movies
            moviesButton = new JButton("View Movies");
            buttonPanel.add(moviesButton);

            moviesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Display movie details
                    showMovieDetails();
                    showReviews(getMovieReviews());
                }
            });
        }

        if(SearchResultScreen.currentSeriesSelected != null){
            // Button to switch to Series
            seriesButton = new JButton("View Series");
            buttonPanel.add(seriesButton);

            seriesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Display series details
                    showSeriesDetails();
                    showReviews(getSeriesReviews());
                }
            });
        }


        // Buttons for submitting reviews, deleting, and editing
        submitReviewButton = new JButton("Submit Review");
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit");

        // Set visibility based on user role
        submitReviewButton.setVisible(!isAdmin);
        deleteButton.setVisible(isAdmin);
        editButton.setVisible(isAdmin);

        buttonPanel.add(submitReviewButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);





        submitReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle submitting a review
                submitReview();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle deleting movie or series (for administrators)
                deleteContent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle editing movie or series (for administrators)
                editContent();
            }
        });

        // Text area to display content details
        contentArea = new JTextArea();
        contentArea.setEditable(false);
        contentArea.setColumns(25);
        contentArea.setRows(7);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        add(contentScrollPane, BorderLayout.CENTER);

        // Panel to display reviews
        JPanel reviewsPanel = new JPanel(new BorderLayout());
        JLabel reviewsLabel = new JLabel("Reviews:");
        reviewsArea = new JTextArea();
        reviewsArea.setEditable(false);
        reviewsArea.setColumns(25);
        reviewsArea.setRows(20);
        JScrollPane reviewsScrollPane = new JScrollPane(reviewsArea);
        reviewsPanel.add(reviewsLabel, BorderLayout.NORTH);
        reviewsPanel.add(reviewsScrollPane, BorderLayout.CENTER);
        add(reviewsPanel, BorderLayout.EAST);

        // Set a specific size for the window
        setSize(800, 600);

        pack();
        setLocationRelativeTo(null);
    }

    private void showMovieDetails() {

        if(SearchResultScreen.currentMovieSelected != null){
            double averageRating = SearchResultScreen.currentMovieSelected.getRating();

            contentArea.setText("Title: " + SearchResultScreen.currentMovieSelected.getTitle() + "\nGenre: " + SearchResultScreen.currentMovieSelected.getCategory() +
                    "\nDuration: " + SearchResultScreen.currentMovieSelected.getDuration() + " minutes\nMature: " + (SearchResultScreen.currentMovieSelected.getIsOver18() ? "Yes" : "No") +
                    "\nDescription: " + SearchResultScreen.currentMovieSelected.getDescription() + "\nYear: " + SearchResultScreen.currentMovieSelected.getFyear() +
                    "\nStarring Actors: " + SearchResultScreen.currentMovieSelected.getActors() +
                    "\n\nAverage Rating: " + averageRating);
        }

    }

    private void showSeriesDetails() {
        if (SearchResultScreen.currentSeriesSelected != null){
            double averageRating = SearchResultScreen.currentSeriesSelected.getRating();

            StringBuilder seriesDetails = new StringBuilder();
            seriesDetails.append("Title: ").append(SearchResultScreen.currentSeriesSelected.getTitle()).append("\nDescription: ").append(SearchResultScreen.currentSeriesSelected.getDescription())
                    .append("\nMature: ").append(SearchResultScreen.currentSeriesSelected.getIsOver18() ? "Yes" : "No").append("\nStarring Actors: ")
                    .append(SearchResultScreen.currentSeriesSelected.getActors()).append("\n");

//            for (Map.Entry<Integer, Integer> entry : series.episodesPerSeason.entrySet()) {
//                int seasonNumber = entry.getKey();
//                int numEpisodes = entry.getValue();
//                int durationPerEpisode = series.durationPerEpisode.get(seasonNumber);
//
//                seriesDetails.append("\nSeason ").append(seasonNumber)
//                        .append("\nNumber of Episodes: ").append(numEpisodes)
//                        .append("\nDuration per Episode: ").append(durationPerEpisode).append(" minutes");
//            }

            seriesDetails.append("\n\nAverage Rating: ").append(averageRating);

            contentArea.setText(seriesDetails.toString());
        }


    }

    private List<Review> getMovieReviews() {

        if(SearchResultScreen.currentMovieSelected != null){
            return SearchResultScreen.currentMovieSelected.getReviews();
        }
        return null;
    }

    private List<Review> getSeriesReviews() {
        if(SearchResultScreen.currentSeriesSelected != null ){
            return SearchResultScreen.currentSeriesSelected.getReviews();
        }
        return null;
    }

    private void showReviews(List<Review> reviews) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault());
        StringBuilder reviewsText = new StringBuilder();
        for (Review review : reviews) {
            reviewsText.append("\n\nRating: ").append(review.getRating())
                    .append("\nReviewer: ").append(review.getUser().getUname())
                    .append("\nReview: ").append(review.getComments());
        }
        reviewsArea.setText(reviewsText.toString());
    }

    private void submitReview() {
        // Implement logic for submitting a review
        JOptionPane.showMessageDialog(this, "Review submitted!");
    }

    private void deleteContent() {

        GuiController.mainUser.delete();

        if(GuiController.reviewRatingForm != null  ){
            GuiController.showReviewRatingForm(false);
        }

        if(GuiController.searchResultScreen != null){
            GuiController.showSearchResultsScreen(false);
        }

        if(GuiController.movieRegistrationForm != null){
            GuiController.showMovieRegistrationForm(false);
        }

        if(GuiController.seriesRegistrationForm != null){
            GuiController.showSeriesRegistrationForm(false);
        }

        GuiController.showViewContentScreen(false);
        // Implement logic for deleting movie or series (for administrators)
        JOptionPane.showMessageDialog(this, "Content deleted!");
    }

    private void editContent() {
        // Implement logic for editing movie or series (for administrators)
        JOptionPane.showMessageDialog(this, "Content edited!");
    }

}
