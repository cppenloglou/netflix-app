package api;

import java.io.Serializable;

public class Rating implements Serializable {
    private User user;
    private Movie movie;
    private Series series;
    private Double rating;

    public Rating(User user , Movie movie, Double rating){
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        user.movieratings.add(this);
    }

    public Rating(User user ,Series series, Double rating){
        this.user = user;
        this.series = series;
        this.rating = rating;
        user.seriesratings.add(this);
    }


    public User getUser(){return user;}
    public Movie getMovie(){
        return movie;
    }
    public Series getSeries(){return series;}
    public Double getRating(){
        return rating;
    }
}
