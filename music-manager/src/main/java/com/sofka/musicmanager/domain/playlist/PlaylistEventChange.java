package com.sofka.musicmanager.domain.playlist;

import com.sofka.musicmanager.domain.generic.EventChange;
import com.sofka.musicmanager.domain.playlist.events.PlaylistCreated;
import com.sofka.musicmanager.domain.playlist.events.SongAdded;

import java.util.ArrayList;

public class PlaylistEventChange implements EventChange {
    public PlaylistEventChange(Playlist playlist){

        listener((PlaylistCreated event) -> {
            playlist.playlistName = event.getName();
            playlist.songList = new ArrayList<>();
        });

        listener((SongAdded event) -> {
            var song = new Song(event.getSongId(),event.getTitle(),event.getLink(),event.getDate(),event.getPreview());
            playlist.songList.add(song);
        });

    }
}
