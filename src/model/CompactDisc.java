package model;

/**
 * Created by Jean-Pierre on 21.10.2016.
 */
public class CompactDisc {
    private String artist;
    private String title;

    public CompactDisc(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
