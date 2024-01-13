package api;

import java.util.ArrayList;
import java.io.Serializable;

public class Season implements Serializable{
    private String name;
    private  int Ep_number, year;
    private ArrayList<Episode> episodes = new ArrayList<>();;

    public Season(String name, int Ep_number, int year) {
        this.name = name;
        this.Ep_number= Ep_number;
        this.year = year;
    }

    public void addEpisode(Episode ep){
        this.episodes.add(ep);
    }
}