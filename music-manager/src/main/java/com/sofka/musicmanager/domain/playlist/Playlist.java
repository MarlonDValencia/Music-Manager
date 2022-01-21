package com.sofka.musicmanager.domain.playlist;
import com.sofka.musicmanager.domain.playlist.events.PlaylistCreated;
import com.sofka.musicmanager.domain.generic.*;
import com.sofka.musicmanager.domain.playlist.events.SongAdded;
import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.List;

public class Playlist extends AggregateRoot {
    protected List<Song> songList;
    protected String playlistName;

    public Playlist(String PlaylistId, String PlaylistName){
        super(PlaylistId);
        subscribe(new PlaylistEventChange(this));
        this.songList = new ArrayList<>();
        appendChange(new PlaylistCreated(PlaylistName)).apply();
    }

    private Playlist(String PlaylistId){
        super(PlaylistId);
        subscribe(new PlaylistEventChange(this));
    }

    public static Playlist from(String playlistId, List<DomainEvent> events){
        var playlist = new Playlist(playlistId);
        events.forEach(playlist::applyEvent);
        return playlist;
    }

    public void addSong(String songId, String title, String link, String date, String preview){
        appendChange(new SongAdded(songId,title,link,date,preview));
    }
    public List<Song> songList(){
        return songList();
    }

    public String PlaylistName(){
        return playlistName;
    }
}
