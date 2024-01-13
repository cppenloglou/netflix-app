package api;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements  Serializable {
    private String name, lastname, username, password;

    protected ArrayList<Series> series = new ArrayList<>();
    protected ArrayList<Rating> movieratings = new ArrayList<>();
    protected ArrayList<Rating> seriesratings = new ArrayList<>();
    protected ArrayList<Movie> favoritesM = new ArrayList<>();
    protected ArrayList<Series> favoritesS = new ArrayList<>();

    protected boolean isAdmin = false;

    public static ArrayList<User> UsersList = new ArrayList<>();

    public User(String Name, String lastname, String username, String password) {
        this.name = Name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;

        User.UsersList.add(this);
    }

    public Boolean getIsAdmin(){
        return isAdmin;
    }

    public void addMovieReview(Movie movie,int selectedRating,String comment){
        Review rw = new Review(this,selectedRating,comment);
        movie.addReview(rw);
    }

    public void addSeriesReview(Series series,int selectedRating,String comment){
        Review rw = new Review(this,selectedRating,comment);
        series.addReview(rw);
    }
    public void addNewFavoriteMovie(Movie movie){
        this.favoritesM.add(movie);
    }
    public void addNewFavoriteSeries(Series series){
        this.favoritesS.add(series);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Series> getSeries() {
        return series;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public static ArrayList<User> getUsersList() {
        return UsersList;
    }

    public static User findUser(String username, String password){
        User userFound = null;

        for(User user : User.UsersList){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                userFound = user;
            }
        }

        return userFound;
    }
    public abstract void delete();

    public abstract void createMovie(Admin user, String title, String desc, Boolean isOver18, String category, String fyear, String duration, String actors, Double rating);

    public abstract void createSeries(Admin user, String title, String desc, boolean isOver18, String category, String actors, Double rating, Season season);
}