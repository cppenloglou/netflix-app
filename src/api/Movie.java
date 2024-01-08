package api;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;


public class Movie implements Serializable {
    transient Scanner scan;
    private String title, desc, category;
    private Boolean isOver18;
    private String actors;
    private User user;
    private int fyear, duration;
    private Double rating;
    private ArrayList<Double> ratings;

    private ArrayList<Review> reviews = new ArrayList<>();

    public static ArrayList<Movie> moviesList = new ArrayList<>();

    public Movie(Admin user, String title, String desc, Boolean isOver18, String category, int fyear, int duration, String actors, Double rating) {
        this.user = user;
        this.title = title;
        this.desc = desc;
        this.isOver18 = isOver18;
        this.category = category;
        this.fyear = fyear;
        this.duration = duration;
        this.actors = actors;
        this.rating = rating;
        this.ratings = new ArrayList<>();
        this.ratings.add(rating);
        scan = new Scanner(System.in);

        //Καθε φορα που δημιουργειτε μια ταινια αυτοματα αποθηκευεται σε αυτη την λιστα, ή οποια επειδη ειναι static ολα τα αντικειμενα εχουν προσβαση σε αυτη.
        Movie.moviesList.add(this);

    }

    public String getDescription(){
        return desc;
    }
    public ArrayList<Review> getReviews(){
        return reviews;
    }
    public String getTitle() {return title;}
    public String getDesc() {return desc;}
    public String getCategory() {return category;}
    public boolean getIsOver18() {return this.isOver18;}
    public String getActors(){return actors;}
    public int getFyear() {return fyear;}
    public int getDuration() {return duration;}
    public Double getRating() {return rating;}


    public void addReview(Review review){
        this.reviews.add(review);
//        Re calculate the rating
        double sum = 0;
        for(Review rw : this.reviews){
            sum = sum + rw.getRating();
        }

        this.rating = this.rating / (this.reviews.size() + 1);
    }

    public static ArrayList<Movie> searchMovies(String title, String actorName,
                                            String minRanking,
                                            boolean isOver18, String selectedGenre) {
        ArrayList<Movie> matchingMovies = new ArrayList<>();


        if(minRanking.isEmpty() && title.isEmpty() && actorName.isEmpty() && selectedGenre.equals("Select")){
            return Movie.moviesList;
        }

        for(Movie movie : Movie.moviesList){
            if(
                    title.equalsIgnoreCase(movie.getTitle()) &&
                    isOver18 == movie.getIsOver18() &&
                            Double.parseDouble(minRanking) <= movie.getRating() &&
                            movie.getActors().contains(actorName) &&
                            movie.getCategory().equalsIgnoreCase(selectedGenre)
            ){
                matchingMovies.add(movie);
            }
        }

        return matchingMovies;
    }
}