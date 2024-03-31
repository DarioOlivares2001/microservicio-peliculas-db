package com.peliculas.peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peliculas.peliculas.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    
}
