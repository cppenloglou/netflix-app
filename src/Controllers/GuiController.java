package Controllers;

import api.User;
import gui.*;

public class GuiController {

    public static User mainUser = null;

    public static LoginForm loginForm;
    public static UserRegistrationForm userRegistrationForm;
    public static ReviewRatingForm reviewRatingForm;
    public static SearchForm searchForm;
    public static SearchResultScreen searchResultScreen;

    public static MovieRegistrationForm movieRegistrationForm;
    public static SeriesRegistrationForm seriesRegistrationForm ;
    public static ViewContentScreen viewContentScreen ;


    public static void showLogInForm(boolean visible){

        if (visible){
            loginForm = new LoginForm();
            loginForm.setVisible(true);
        }else{
            loginForm.dispose();
        }

    }

    public static void showUserRegistrationForm(boolean visible){
        if (visible){
            userRegistrationForm = new UserRegistrationForm();
            userRegistrationForm.setVisible(true);
        }else{
            userRegistrationForm.dispose();
        }
    }

    public static void showSearchScreen(boolean visible) {
        if (visible){
            searchForm = new SearchForm();
            searchForm.setVisible(true);
        }else{
            searchForm.dispose();
        }

    }

    public static void showSearchResultsScreen(boolean visible){
        if (visible){
            searchResultScreen = new SearchResultScreen();
            searchResultScreen.setVisible(true);
        }else{
            searchResultScreen.dispose();
        }
    }

    public static void showReviewRatingForm(boolean visible) {
        if (visible){
            reviewRatingForm = new ReviewRatingForm();
            reviewRatingForm.setVisible(true);
        }else{
            reviewRatingForm.dispose();
        }

    }

//    ADMIN

    public static void showMovieRegistrationForm(boolean visible) {
        if (visible){
            movieRegistrationForm = new MovieRegistrationForm();
            movieRegistrationForm.setVisible(true);
        }else{
            movieRegistrationForm.dispose();
        }

    }

    public static void showSeriesRegistrationForm(boolean visible) {
        if (visible){
            seriesRegistrationForm = new SeriesRegistrationForm();
            seriesRegistrationForm.setVisible(true);
        }else{
            seriesRegistrationForm.dispose();
        }

    }

    public static void showViewContentScreen(boolean visible) {
        if (visible){
            viewContentScreen = new ViewContentScreen(true);
            viewContentScreen.setVisible(true);
        }else{
            viewContentScreen.dispose();
        }

    }
}
