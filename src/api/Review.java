package api;

import java.io.Serializable;

public class Review implements Serializable {

    private User user;
    private int rating;
    private String comments;

    public Review(User user, int rating, String comments){
        this.user = user;
        this.rating = rating;
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
