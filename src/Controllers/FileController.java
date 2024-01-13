package Controllers;

import api.*;

import java.io.*;
import java.util.ArrayList;

//    Η κλάση αυτή έχει όλες τις λειτουργίες για τη διαχείριση των αρχείων του προγράμματος.
//    Εμπεριέχει τα ονόματα των αρχείων που αποθηκεύονται τα δεδομένα και όλες τις συναρτήσεις για γράψιμο/διάβασμα αυτών των δεδομένων.
public class FileController {

    //    File Variables
    private static final String moviesFilename = "movies.ser";
    private static final String seriesFilename = "series.ser";
    private static final String usersFilename = "users.ser";

    // Function to write Movie objects to a file
    public static void writeMoviesToFile(ArrayList<Movie> movies) {


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileController.moviesFilename))) {
            oos.writeObject(movies);
            System.out.println("Movies have been written to " + FileController.moviesFilename);
        } catch (IOException ignored) {
        }
    }

    // Function to read Movie objects from a file and return as ArrayList<Movie>
    public static ArrayList<Movie> readMoviesFromFile() {
        ArrayList<Movie> movies = new ArrayList<>();

        File file = new File(FileController.moviesFilename);

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                movies = (ArrayList<Movie>) ois.readObject();
                System.out.println("Movies have been read from " + FileController.moviesFilename);
            } catch (IOException ignored) {
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("File does not exist. Creating an empty file.");
            writeMoviesToFile(movies);
        }

        return movies;
    }


    // Function to write Series objects to a file
    public static void writeSeriesToFile(ArrayList<Series> seriesList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileController.seriesFilename))) {
            oos.writeObject(seriesList);
            System.out.println("Series have been written to " + FileController.seriesFilename);
        } catch (IOException ignored) {
        }
    }

    // Function to read Series objects from a file and return as ArrayList<Series>
    public static ArrayList<Series> readSeriesFromFile() {
        ArrayList<Series> seriesList = new ArrayList<>();

        File file = new File(seriesFilename);

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                seriesList = (ArrayList<Series>) ois.readObject();
                System.out.println("Series have been read from " + seriesFilename);
            } catch (IOException ignored) {
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("File does not exist. Creating an empty file.");
            writeSeriesToFile(seriesList);
        }

        return seriesList;
    }


    // Function to write User objects to a file
    public static void writeUsersToFile(ArrayList<User> userList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(usersFilename))) {
            oos.writeObject(userList);
            System.out.println("Users have been written to " + usersFilename);
        } catch (IOException ignored) {
        }
    }


    // Function to read User objects from a file and return as ArrayList<User>
    public static ArrayList<User> readUsersFromFile() {
        ArrayList<User> userList = new ArrayList<>();

        File file = new File(usersFilename);

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                userList = (ArrayList<User>) ois.readObject();
                System.out.println("Users have been read from " + usersFilename);
            } catch (IOException | ClassNotFoundException ignored) {
            }
        } else {
            System.out.println("File does not exist. Creating an empty file.");
            writeUsersToFile(userList);
        }

        return userList;
    }

    public static void createDefaultValues(){
//      Default Users
        Admin admin1 = new Admin("admin1", "admin1", "admin1", "12345678");
        Admin admin2 = new Admin("admin2", "admin2", "admin2", "12345678");
        User sub1 = new Sub("sub1", "sun1", "sub1", "12345678");
        User sub2 = new Sub("sub2", "sun2", "sub2", "12345678");

//      Default Movies
        Movie movie1 = new Movie(admin1, "Oppenheimer", "Desc", false, "Thriller", 2023, 180, "Cillian Murphy", 5.0);
        Movie movie2 = new Movie(admin2, "Barbie", "Desc 3", true, "Comedy", 2023, 120, "Margot Robbie", 4.0);

//      Default Seasons
        Season season1 = new Season("First Season", 5, 2023);
        season1.addEpisode(new Episode(20));

        Season season2 = new Season("Second season", 10, 2024);
        season2.addEpisode(new Episode(30));

//        Default Series
        Series series1 = new Series(admin2, "Big Bang Theory", "Desc2", true, "Sci=Fi", "Johnny Galerkin, ames Joseph Parsons", 5.0);
        series1.addSeason(season1);

//        ADD DEFAULT DATA
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        FileController.writeMoviesToFile(movies);


        ArrayList<User> users = new ArrayList<>();
        users.add(admin1);
        users.add(admin2);
        users.add(sub1);
        users.add(sub2);
        FileController.writeUsersToFile(users);

        ArrayList<Series> series = new ArrayList<>();
        series.add(series1);
        FileController.writeSeriesToFile(series);
    }
}
