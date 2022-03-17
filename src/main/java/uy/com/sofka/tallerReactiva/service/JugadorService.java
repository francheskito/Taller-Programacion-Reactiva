package uy.com.sofka.tallerReactiva.service;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import uy.com.sofka.tallerReactiva.model.JugadorMongo;
import uy.com.sofka.tallerReactiva.repository.JugadorRepository;

@Service
public class JugadorService {
    @Autowired
    JugadorRepository jugadorRepository;
    public Flux<JugadorMongo> obtenerJugadoresMayoresA34AñosDeEdad(){
        Flux<JugadorMongo> playersOlderThan34 = jugadorRepository.findAll()
                .buffer(120)
                .flatMap(jugador -> Flux.fromStream(jugador.parallelStream()))
                .filter(jugador -> jugador.getAge() > 34);
        return playersOlderThan34;
    }
    public Flux<JugadorMongo> obtenerJugadores(){
        Flux<JugadorMongo> jugadores = jugadorRepository.findAll()
                .buffer(120)
                .flatMap(jugador -> Flux.fromStream(jugador.parallelStream()));
        return jugadores;
    }
}