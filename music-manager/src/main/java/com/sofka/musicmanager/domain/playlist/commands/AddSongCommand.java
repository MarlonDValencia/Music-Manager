package com.sofka.musicmanager.domain.playlist.commands;
import  com.sofka.musicmanager.domain.generic.Command;
public class AddSongCommand extends Command {
    private String playlistId;
    private String songId;
    private String title;
    private String link;
    private String date;
    private String preview;

    public AddSongCommand(){
    }

    public String getPlaylistId() {
        return playlistId;
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

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
