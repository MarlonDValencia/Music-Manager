package com.sofka.musicmanager.infra.materialize;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoClient;
import com.sofka.musicmanager.domain.playlist.events.PlaylistCreated;
import com.sofka.musicmanager.domain.playlist.events.SongAdded;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ProgramHandle {
    private final MongoClient mongoClient;

    public ProgramHandle(MongoClient mongoClient){
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value = "com.sofka.playlistcreated", blocking = true)
        void consumePlaylistCreated(PlaylistCreated event){
        Map<String,  Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("playlistName",event.getName());

        mongoClient.getDatabase("queries")
                .getCollection("playlist")
                .insertOne(new Document(document));
    }

    @ConsumeEvent(value = "com.sofka.songadded")
    void consumeSongAdded(SongAdded event){
        BasicDBObject document = new BasicDBObject();
        document.put("song."+event.getTitle()+".id",event.getSongId());
        document.put("song."+event.getTitle()+".tittle",event.getTitle());
        document.put("song."+event.getTitle()+".link",event.getLink());
        document.put("song."+event.getTitle()+".preview",event.getPreview());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("playlist")
                .updateOne(Filters.eq("_id",event.getAggregateId()),updateObject);
    }
}
