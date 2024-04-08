package com.peliculas.peliculas.service;



import java.util.List;
import java.util.Optional;

import com.peliculas.peliculas.model.Pelicula;

public interface PeliculaService {
    
    List<Pelicula> getAllPeliculas();
    Optional<Pelicula> getPeliculaById(Long id);
    Pelicula createPelicula(Pelicula pelicula);
    Pelicula updatePelicula(long id, Pelicula pelicula);
    void deletePelicula(Long id);
    
}
