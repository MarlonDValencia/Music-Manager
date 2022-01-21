package com.sofka.musicmanager.infra.generic;

import com.sofka.musicmanager.domain.generic.DomainEvent;
import com.sofka.musicmanager.domain.generic.EventSerializer;
import com.sofka.musicmanager.domain.generic.EventStoreRepository;
import com.sofka.musicmanager.domain.generic.StoredEvent;
import com.sofka.musicmanager.infra.message.BusService;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public abstract class UseCaseHandle {

    @Inject
    private EventStoreRepository repository;

    @Inject
    private BusService busService;

    public void savePlaylist(String playlistId, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("catalogo", playlistId, storedEvent));

        events.forEach(busService::send);
    }
}
