package api;

import java.util.ArrayList;
import java.io.Serializable;

public class Season implements Serializable{
     private String name;
     int epnumber, fyear;
    ArrayList<Episode> episodes;

    public Season(String name, int epnumber, int fyear) {
        this.name = name;
        this.epnumber = epnumber;
        this.fyear = fyear;
        this.episodes = episodes;
        episodes = new ArrayList<>();
    }
    public ArrayList<Episode> getEpisode(){return episodes;}
    public String getName(){return name;}
    public int getFyear(){return fyear;}

    public void addEpisode(Episode ep){
        this.episodes.add(ep);
    }
}