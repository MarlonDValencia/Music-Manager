package com.sofka.musicmanager.useCases;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sofka.musicmanager.domain.generic.DomainEvent;
import com.sofka.musicmanager.domain.generic.EventStoreRepository;
import com.sofka.musicmanager.domain.playlist.Playlist;
import com.sofka.musicmanager.domain.playlist.commands.AddSongCommand;

import javax.enterprise.context.Dependent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

@Dependent
public class AddSongUseCase implements Function<AddSongCommand, List<DomainEvent>> {
    private final EventStoreRepository eventStoreRepository;
    private String URL_BASE = "https://api.deezer.com/search?q=eminem";
    private String response = "";
    Logger logger = Logger.getLogger("MyLog");

    String title;
    String link;
    String date;
    String preview;

    public AddSongUseCase(EventStoreRepository eventStoreRepository){
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public List<DomainEvent> apply(AddSongCommand addSongCommand){
        var playlist = Playlist.from(addSongCommand.getPlaylistId(),eventStoreRepository.getEventsBy("playlist",addSongCommand.getPlaylistId()));

        try{
            response = request(URL_BASE);
            JsonObject cancion = new JsonParser().parse(response).getAsJsonObject();
            JsonArray jsonCanciones = cancion.get("data").getAsJsonArray();

            for (int i = 0; i < jsonCanciones.size(); i++){
                JsonObject entry = jsonCanciones.get(i).getAsJsonObject();
                title = entry.get("title").getAsString();
                link = entry.get("link").getAsString();
                preview = entry.get("preview").getAsString();
                logger.info("Log to test");
                var id = entry.get("id").getAsString();
                playlist.addSong(id,title,link,date,preview);
                System.out.println(entry);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return playlist.getUncommittedChanges();
    }

    public static String request(String Url) throws Exception {

        StringBuilder result = new StringBuilder();
        URL url = new URL(Url);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}
