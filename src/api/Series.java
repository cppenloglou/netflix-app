package api;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Series implements Serializable {
    transient Scanner scan;
    private String title, desc, category;
    private boolean isOver18;
    private String actors;
    private ArrayList<Season> seasons = new ArrayList<>();
    private User user;
    private Double rating;
    private ArrayList<Double> ratings;
    private ArrayList<Review> reviews = new ArrayList<>();

    // Oi
    public static ArrayList<Series> SeriesList = new ArrayList<>();

    public Series(Admin user, String title, String desc, boolean isOver18, String category, String actors, Double rating) {
        this.user = user;
        this.title = title;
        this.desc = desc;
        this.isOver18 = isOver18;
        this.category = category;
        this.actors = actors;
        this.rating = rating;
        this.ratings = new ArrayList<>();
        this.ratings.add(rating);
        scan = new Scanner(System.in);

        Series.SeriesList.add(this);

    }
    public String getDescription(){
        return desc;
    }
    public void addSeason(Season season){
        this.seasons.add(season);
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
        return category;
    }

    public boolean getIsOver18() {
        return isOver18;
    }

    public String getActors() {
        return actors;
    }
    public Double getRating(){
        return rating;
    }

    public ArrayList<Season> getSeasons() {return seasons;}

    public static ArrayList<Series> searchSeries(String title, String actorName,
                                                String minRanking,
                                                boolean isOver18, String selectedGenre) {
        ArrayList<Series> matchingSeries = new ArrayList<>();


        if(minRanking.isEmpty() && title.isEmpty() && actorName.isEmpty() && selectedGenre.equals("Select")){
            return Series.SeriesList;
        }

        for(Series series : Series.SeriesList){
            if(
                    title.equalsIgnoreCase(series.getTitle()) &&
                            isOver18 == series.getIsOver18() &&
                            Double.parseDouble(minRanking) <= series.getRating() &&
                            series.getActors().contains(actorName) &&
                            series.getCategory().equalsIgnoreCase(selectedGenre)
            ){
                matchingSeries.add(series);
            }
        }

        return matchingSeries;
    }
    public ArrayList<Review> getReviews(){
        return reviews;
    }
    public void addReview(Review review){
        this.reviews.add(review);
//        Re calculate the rating
        double sum = 0;
        for(Review rw : this.reviews){
            sum = sum + rw.getRating();
        }

        this.rating = this.rating / (this.reviews.size() + 1);
    }
}
