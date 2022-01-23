import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchPlaylist } from "../actions/index.js";

function Playlist() {
  const dispatch = useDispatch();
  const playlist = useSelector((state) => state.playlist);

  useEffect(() => {
    const socket = new WebSocket(
      "ws://" + "localhost:8080" + "/retrieve/" + "xxxx-xxxx"
    );
    socket.onmessage = function (m) {
      const data = JSON.parse(m.data);
      console.log(data.type);
      dispatch(fetchPlaylist());
    };
  }, []);
  return (
    <div>
      <h1>Wanna listen to some tunes?</h1>
      {playlist.length > 0
        ? playlist.map((song) => {
            return (
              <div>
                <h2 className="display-4">{song.tittle}</h2>
                <a href={song.preview}>Preview</a>
                <br />
                <a href={song.link}>link</a>
                <iframe
                  title="deezer-widget"
                  src={`https://widget.deezer.com/widget/dark/track/${song.id}`}
                  width="100%"
                  height="300"
                  frameborder="0"
                  allowtransparency="true"
                  allow="encrypted-media; clipboard-write"
                ></iframe>
              </div>
            );
          })
        : "No se han encontrado canciones..."}
    </div>
  );
}

export default Playlist;
