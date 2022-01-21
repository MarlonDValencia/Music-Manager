package com.sofka.musicmanager.domain.playlist.commands;
import  com.sofka.musicmanager.domain.generic.Command;

public class CreatePlaylistCommnad extends Command {
    private  String PlaylistId;
    private  String PlaylistName;

    public CreatePlaylistCommnad(){
    }

    public String getPlaylistId(){
        return  this.PlaylistId;
    }

    public String getPlaylistName() {
        return this.PlaylistName;
    }

    public void setPlaylistId(String playlistId) {
        this.PlaylistId = playlistId;
    }

    public void setPlaylistName(String playlistName) {
        this.PlaylistName = playlistName;
    }
}
