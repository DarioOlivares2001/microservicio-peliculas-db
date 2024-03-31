package com.peliculas.peliculas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.service.PeliculaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/peliculas")
public class PeliculaController {


    @Autowired
    private PeliculaService pelilculaService;

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        
        return pelilculaService.getAllPeliculas();
    }

    @GetMapping("/{id}")
    public Optional<Pelicula> getPeliculaById(@PathVariable Long id) {
        return pelilculaService.getPeliculaById(id);
    }
    


    
    
}
