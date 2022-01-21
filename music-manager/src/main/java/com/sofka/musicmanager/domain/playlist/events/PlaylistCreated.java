package com.sofka.musicmanager.domain.playlist.events;
import com.sofka.musicmanager.domain.generic.*;
public class PlaylistCreated extends DomainEvent {

    private String playlistName;

    public PlaylistCreated(String Name){
        super("com.sofka.playlistcreated");
        this.playlistName = Name;
    }

    public String getName() {
        return playlistName;
    }
}
