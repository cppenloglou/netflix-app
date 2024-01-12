import Controllers.FileController;
import Controllers.GuiController;
import api.Movie;
import api.Series;
import api.User;

import java.io.IOException;
public class Main {

    public static void main(String[] args) throws IOException {

        // CREATE DEFAULT DATA
        // FileController.createDefaultValues();

        // READ DATA FROM FILES
        User.UsersList = FileController.readUsersFromFile();
        Movie.moviesList = FileController.readMoviesFromFile();
        Series.SeriesList = FileController.readSeriesFromFile();

        // START THE PROGRAM
        GuiController.showLogInForm(true);
    }
}