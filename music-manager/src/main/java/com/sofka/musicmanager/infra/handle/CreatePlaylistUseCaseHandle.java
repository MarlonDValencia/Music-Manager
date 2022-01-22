package com.sofka.musicmanager.infra.handle;

import com.sofka.musicmanager.domain.playlist.commands.CreatePlaylistCommnad;
import com.sofka.musicmanager.infra.generic.UseCaseHandle;
import com.sofka.musicmanager.useCases.CreatePlaylistUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreatePlaylistUseCaseHandle extends UseCaseHandle{
    private final CreatePlaylistUseCase createPlaylistUseCase;

    public CreatePlaylistUseCaseHandle(CreatePlaylistUseCase createPlaylistUseCase){
        this.createPlaylistUseCase = createPlaylistUseCase;
    }
    @ConsumeEvent(value = "com.sofka.createplaylist")
    void consumeBlocking(CreatePlaylistCommnad createPlaylistCommnad){
        var events = createPlaylistUseCase.apply(createPlaylistCommnad);
        savePlaylist(createPlaylistCommnad.getPlaylistId(), events);
    }
}
