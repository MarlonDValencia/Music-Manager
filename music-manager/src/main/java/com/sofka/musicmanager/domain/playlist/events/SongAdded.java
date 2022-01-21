package com.sofka.musicmanager.domain.playlist.events;
import  com.sofka.musicmanager.domain.generic.DomainEvent;

public class SongAdded extends DomainEvent{
    private String songId;
    private String title;
    private String link;
    private String date;
    private String preview;

    public SongAdded(String songId, String title, String link, String date, String preview) {
        super("com.sofka.songadded");
        this.songId = songId;
        this.title = title;
        this.link = link;
        this.date = date;
        this.preview = preview;
    }


    public String getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public String getPreview() {
        return preview;
    }
}
