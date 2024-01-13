package api;

import java.io.Serializable;
public class Sub extends User implements Serializable {
    public Sub(String name, String lastname, String username, String password) {
        super(name, lastname, username, password);
        this.isAdmin = false;
    }

    @Override
    public void delete() {}

    @Override
    public void createMovie(Admin user, String title, String desc, Boolean isOver18, String category, String fyear, String duration, String actors, Double rating) {}

    @Override
    public void createSeries(Admin user, String title, String desc, boolean isOver18, String category, String actors, Double rating, Season season) {}

}