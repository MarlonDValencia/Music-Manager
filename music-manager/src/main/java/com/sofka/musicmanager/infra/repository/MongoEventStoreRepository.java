package com.sofka.musicmanager.infra.repository;
import com.mongodb.Function;
import com.mongodb.client.MongoClient;
import com.sofka.musicmanager.domain.generic.DomainEvent;
import com.sofka.musicmanager.domain.generic.EventSerializer;
import com.sofka.musicmanager.domain.generic.EventStoreRepository;
import com.sofka.musicmanager.domain.generic.StoredEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class MongoEventStoreRepository implements EventStoreRepository {
    private final MongoClient mongoClient;

    public MongoEventStoreRepository(MongoClient mongoClient){
        this.mongoClient = mongoClient;
    }
    @Override
    public List<DomainEvent> getEventsBy(String aggregateName, String aggregateRootId) {
        List<DomainEvent> events = new ArrayList<>();
        //TODO: ordenar por fecha
        mongoClient.getDatabase("command")
                .getCollection(aggregateName)
                .find(eq("aggregateId", aggregateRootId))
                .map((Function<Document, DomainEvent>) document -> {
                    var eventBody = document.get("eventBody");
                    try {
                        return EventSerializer
                                .instance()
                                .deserialize(eventBody.toString(), Class.forName(document.get("typeName").toString()));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(events::add);
        return events;
    }

    @Override
    public void saveEvent(String aggregateName, String aggregateRootId, StoredEvent storedEvent) {
        Map<String, Object> document = new HashMap<>();

        document.put("_id", UUID.randomUUID().toString());
        document.put("aggregateId", aggregateRootId);
        document.put("occurredOn", storedEvent.getOccurredOn());
        document.put("typeName", storedEvent.getTypeName());
        document.put("eventBody", storedEvent.getEventBody());

        mongoClient.getDatabase("command").getCollection(aggregateName).insertOne(new Document(document));
    }
}
