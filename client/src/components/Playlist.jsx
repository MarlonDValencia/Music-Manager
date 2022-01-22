import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { fetchPlaylist } from '../actions/index.js';

function Playlist() {

    const dispatch = useDispatch();
    const catalogo = useSelector((state) => state.playlist);

    useEffect(() => {
        const socket = new WebSocket('ws://' + 'localhost:8080' + '/retrieve/' + 'xxxx-xxxx')
        socket.onmessage = function (m) {
            const data = JSON.parse(m.data)
            console.log('Got message: ' + data.type)
            dispatch(fetchPlaylist());
        }
    }, [])

    return (
        <div className="contenedor_general_catalogo">
            <h1 className="titulo_catalogo">¿Qué quieres leer hoy?</h1>
            {catalogo.length > 0 ? (
                catalogo.map(libro => {
                    return(
                        <div className="contenedor_libro">
                            <div class="row g-0">
                                    <div class="col-md-3">
                                        <a class="navbar-brand" href={libro.urllectura}>
                                            <img src={libro.imagen} class="img-fluid rounded-start" alt="imagen_libro" />
                                        </a>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="card-body">
                                            <h2 class="card-title">{libro.titulo}</h2>
                                            <h3 class="card-title" id="alinear-texto">{libro.autor}</h3>
                                            <h4 class="card-title">Género: {libro.genero}</h4>
                                            <h5 class="card-title">Editorial: {libro.editorial}</h5>
                                            <h6 class="card-title">Número de páginas: {libro.paginas}</h6>
                                            <p class="card-text" id="alinear-texto" >Descripción: {libro.descripcion}</p>
                                            <p class="card-text"><small class="text-muted">Fecha de lanzamiento: {libro.fechalanzamiento}</small></p>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    )
                })
            ):("a")}
        </div>
    )   
}

export default Playlist;