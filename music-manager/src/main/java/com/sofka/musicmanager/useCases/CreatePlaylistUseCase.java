package com.sofka.musicmanager.useCases;

import com.sofka.musicmanager.domain.generic.DomainEvent;
import com.sofka.musicmanager.domain.playlist.Playlist;
import com.sofka.musicmanager.domain.playlist.commands.CreatePlaylistCommnad;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CreatePlaylistUseCase implements Function<CreatePlaylistCommnad, List<DomainEvent>> {
    @Override
    public List<DomainEvent> apply(CreatePlaylistCommnad createPlaylistCommnad){
        var playlist = new Playlist(createPlaylistCommnad.getPlaylistId(),createPlaylistCommnad.getPlaylistName());
        return playlist.getUncommittedChanges();
    }
}
