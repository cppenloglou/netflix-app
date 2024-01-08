package api;

import java.io.Serializable;
public class Episode implements Serializable {
    int duration;

    public Episode(int duration) {
        this.duration = duration;
    }

    public int getDuration(){return duration;}
}
