package com.sofka.musicmanager.infra.entrypoint;

import com.sofka.musicmanager.domain.playlist.commands.AddSongCommand;
import com.sofka.musicmanager.domain.playlist.commands.CreatePlaylistCommnad;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class CommandController {

    private final EventBus bus;

    public CommandController(EventBus bus){
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addsong")
    public Response executor(AddSongCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createplaylist")
    public Response executor(CreatePlaylistCommnad command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }
}