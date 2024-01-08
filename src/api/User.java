package api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements  Serializable {
    transient Scanner scan;
    private String name;
    private String sname;
    private String uname;
    private String pass;
    protected ArrayList<Movie> movies = new ArrayList<>();

    protected ArrayList<Series> series = new ArrayList<>();
    protected ArrayList<Rating> movieratings = new ArrayList<>();
    protected ArrayList<Rating> seriesratings = new ArrayList<>();
    protected ArrayList<Movie> favoritesM = new ArrayList<>();
    protected ArrayList<Series> favoritesS = new ArrayList<>();

    protected boolean isAdmin = false;

    public static ArrayList<User> UsersList = new ArrayList<>();

    public User(String Name, String Sname, String Uname, String Pass) {
        name = Name;
        sname = Sname;
        uname = Uname;
        pass = Pass;

        scan = new Scanner(System.in);

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
    public String getName() {
        return name;
    }

    public String getSname() {
        return sname;
    }

    public String getUname() {
        return uname;
    }

    public String getPass() {
        return pass;
    }


    public User Login(String username, String password, ArrayList<User> users) {
        int i = 0;
        for (User u : users) {
            if (username.equals(users.get(i).getUname()) && password.equals(users.get(i).getPass())) {
                u.name = users.get(i).getName();
                u.sname = users.get(i).getSname();
                u.uname = users.get(i).getUname();
                u.pass = users.get(i).getPass();
                return u;
            }i++;
        }return null;
    }

    public static User findUser(String username, String password){
        User userFound = null;

        for(User user : User.UsersList){
            if (user.getUname().equals(username) && user.getPass().equals(password)){
                userFound = user;
            }
        }

        return userFound;
    }
    public void delete(){}

    public void createMovie(Admin user, String title, String desc, Boolean isOver18, String category, String fyear, String duration, String actors, Double rating){
    }

    public void createSeries(Admin user, String title, String desc, boolean isOver18, String category, String actors, Double rating, Season season){

    }
}