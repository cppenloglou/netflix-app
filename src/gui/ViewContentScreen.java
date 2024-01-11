import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

class Content {
    String title;

    public Content(String title) {
        this.title = title;
    }
}

class Movie extends Content {
    String genre;
    int duration;
    boolean isMature; // true if mature, false if not
    String description;
    int year;
    String starringActors;
    List<Review> reviews;

    public Movie(String title, String genre, int duration, boolean isMature, String description, int year, String starringActors) {
        super(title);
        this.genre = genre;
        this.duration = duration;
        this.isMature = isMature;
        this.description = description;
        this.year = year;
        this.starringActors = starringActors;
        this.reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        }

        int totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.rating;
        }

        return (double) totalRating / reviews.size();
    }
}

class Series extends Content {
    Map<Integer, Integer> episodesPerSeason; // Map season number to number of episodes
    Map<Integer, Integer> durationPerEpisode; // Map season number to duration of each episode
    String description;
    boolean isMature; // true if mature, false if not
    String starringActors;
    List<Review> reviews;

    public Series(String title, String description, boolean isMature, String starringActors) {
        super(title);
        this.episodesPerSeason = new HashMap<>();
        this.durationPerEpisode = new HashMap<>();
        this.description = description;
        this.isMature = isMature;
        this.starringActors = starringActors;
        this.reviews = new ArrayList<>();
    }

    public void addSeasonDetails(int seasonNumber, int numEpisodes, int durationPerEpisode) {
        episodesPerSeason.put(seasonNumber, numEpisodes);
        this.durationPerEpisode.put(seasonNumber, durationPerEpisode);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        }

        int totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.rating;
        }

        return (double) totalRating / reviews.size();
    }
}

class Review {
    String content;
    int rating;
    Date reviewDate;
    String reviewerUsername;

    public Review(String content, int rating, Date reviewDate, String reviewerUsername) {
        this.content = content;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.reviewerUsername = reviewerUsername;
    }
}

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

        // Button to switch to Movies
        moviesButton = new JButton("View Movies");
        buttonPanel.add(moviesButton);

        // Button to switch to Series
        seriesButton = new JButton("View Series");
        buttonPanel.add(seriesButton);

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

        moviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display movie details
                showMovieDetails();
                showReviews(getMovieReviews());
            }
        });

        seriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display series details
                showSeriesDetails();
                showReviews(getSeriesReviews());
            }
        });

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
        Movie movie = new Movie("Movie Title", "Action", 120, true, "A description of the movie.", 2023, "Actor1, Actor2");
        movie.addReview(new Review("Great movie!", 5, new Date(), "User1"));
        movie.addReview(new Review("The plot was confusing.", 3, new Date(), "User2"));
        movie.addReview(new Review("Not worth watching.", 1, new Date(), "User3"));

        double averageRating = movie.getAverageRating();

        contentArea.setText("Title: " + movie.title + "\nGenre: " + movie.genre +
                "\nDuration: " + movie.duration + " minutes\nMature: " + (movie.isMature ? "Yes" : "No") +
                "\nDescription: " + movie.description + "\nYear: " + movie.year +
                "\nStarring Actors: " + movie.starringActors +
                "\n\nAverage Rating: " + averageRating);
    }

    private void showSeriesDetails() {
        Series series = new Series("Series Title", "A description of the series.", true, "Actor1, Actor2");
        series.addSeasonDetails(1, 10, 30); // Season 1 has 10 episodes, each with a duration of 30 minutes
        series.addSeasonDetails(2, 12, 25); // Season 2 has 12 episodes, each with a duration of 25 minutes
        series.addReview(new Review("Awesome series!", 5, new Date(), "User1"));
        series.addReview(new Review("Each episode is a masterpiece.", 4, new Date(), "User2"));
        series.addReview(new Review("Could be better.", 3, new Date(), "User3"));

        double averageRating = series.getAverageRating();

        StringBuilder seriesDetails = new StringBuilder();
        seriesDetails.append("Title: ").append(series.title).append("\nDescription: ").append(series.description)
                .append("\nMature: ").append(series.isMature ? "Yes" : "No").append("\nStarring Actors: ")
                .append(series.starringActors).append("\n");

        for (Map.Entry<Integer, Integer> entry : series.episodesPerSeason.entrySet()) {
            int seasonNumber = entry.getKey();
            int numEpisodes = entry.getValue();
            int durationPerEpisode = series.durationPerEpisode.get(seasonNumber);

            seriesDetails.append("\nSeason ").append(seasonNumber)
                    .append("\nNumber of Episodes: ").append(numEpisodes)
                    .append("\nDuration per Episode: ").append(durationPerEpisode).append(" minutes");
        }

        seriesDetails.append("\n\nAverage Rating: ").append(averageRating);

        contentArea.setText(seriesDetails.toString());
    }

    private List<Review> getMovieReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("Great movie!", 5, new Date(), "User1"));
        reviews.add(new Review("The plot was confusing.", 3, new Date(), "User2"));
        reviews.add(new Review("Not worth watching.", 1, new Date(), "User3"));
        return reviews;
    }

    private List<Review> getSeriesReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("Awesome series!", 5, new Date(), "User1"));
        reviews.add(new Review("Each episode is a masterpiece.", 4, new Date(), "User2"));
        reviews.add(new Review("Could be better.", 3, new Date(), "User3"));
        return reviews;
    }

    private void showReviews(List<Review> reviews) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault());
        StringBuilder reviewsText = new StringBuilder();
        for (Review review : reviews) {
            reviewsText.append("\n\nRating: ").append(review.rating)
                    .append("\nReviewer: ").append(review.reviewerUsername)
                    .append("\nDate: ").append(dateFormat.format(review.reviewDate))
                    .append("\nReview: ").append(review.content);
        }
        reviewsArea.setText(reviewsText.toString());
    }

    private void submitReview() {
        // Implement logic for submitting a review
        JOptionPane.showMessageDialog(this, "Review submitted!");
    }

    private void deleteContent() {
        // Implement logic for deleting movie or series (for administrators)
        JOptionPane.showMessageDialog(this, "Content deleted!");
    }

    private void editContent() {
        // Implement logic for editing movie or series (for administrators)
        JOptionPane.showMessageDialog(this, "Content edited!");
    }

}
