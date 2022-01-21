package com.sofka.musicmanager.domain.playlist;

public class Song {
    private String songId;
    private String title;
    private String link;
    private String date;
    private String preview;

    public Song(String songId, String title, String link, String date, String preview) {
        this.songId = songId;
        this.title = title;
        this.link = link;
        this.date = date;
        this.preview = preview;
    }

    public String songId() {
        return songId;
    }

    public String title() {
        return title;
    }

    public String link() {
        return link;
    }

    public String date() {
        return date;
    }

    public String preview() {
        return preview;
    }
}
