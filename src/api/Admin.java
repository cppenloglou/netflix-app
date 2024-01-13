package api;

import Controllers.FileController;

import java.io.Serializable;
public class Admin extends User implements Serializable {
    public Admin(String name, String lastname, String username, String password) {
        super(name, lastname, username, password);
        this.isAdmin = true;
    }

    @Override
    public void delete() {}

    public void createMovie(Admin user, String title, String desc, Boolean isOver18, String category, String fyear, String duration, String actors, Double rating){
        Movie mv = new Movie(user,  title, desc,  isOver18, category,  Integer.parseInt(fyear),  Integer.parseInt(duration),  actors,  rating);

        FileController.writeMoviesToFile(Movie.moviesList);
    }

    public void createSeries(Admin user, String title, String desc, boolean isOver18, String category, String actors, Double rating, Season season){
        Series sr = new Series(user, title, desc, isOver18, category, actors, rating);
        sr.addSeason(season);

        FileController.writeSeriesToFile(Series.SeriesList);
    }

}
