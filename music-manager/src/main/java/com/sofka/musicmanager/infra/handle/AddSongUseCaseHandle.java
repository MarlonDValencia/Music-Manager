package com.sofka.musicmanager.infra.handle;

import com.sofka.musicmanager.domain.playlist.commands.AddSongCommand;
import com.sofka.musicmanager.domain.playlist.commands.CreatePlaylistCommnad;
import com.sofka.musicmanager.infra.generic.UseCaseHandle;
import com.sofka.musicmanager.useCases.AddSongUseCase;
import com.sofka.musicmanager.useCases.CreatePlaylistUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddSongUseCaseHandle extends UseCaseHandle {

    private final AddSongUseCase addSongUseCase;

    public AddSongUseCaseHandle(AddSongUseCase addSongUseCase){
        this.addSongUseCase = addSongUseCase;
    }

    @ConsumeEvent(value = "com.sofka.songadded")
    void consumeBlocking(AddSongCommand addSongCommand){
        var events = addSongUseCase.apply(addSongCommand);
        savePlaylist(addSongCommand.getPlaylistId(),events);
    }
}