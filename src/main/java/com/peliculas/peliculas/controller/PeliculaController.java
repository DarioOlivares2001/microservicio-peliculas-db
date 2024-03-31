package com.peliculas.peliculas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.service.PeliculaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/peliculas")
public class PeliculaController {


    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        
        return peliculaService.getAllPeliculas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPeliculaById(@PathVariable Long id) {

        Optional<Pelicula> peliculas = peliculaService.getPeliculaById(id);
        if(peliculas.isPresent())
        {
            return ResponseEntity.ok(peliculas) ;    
        }else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la pelicula " + id.toString()  + "");

        }
        
    }
      
    


    
    
}
