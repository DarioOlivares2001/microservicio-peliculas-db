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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        
        return peliculaService.createPelicula(pelicula);
    }

    @PutMapping("/{id}")
    public Pelicula updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) 
    {
        return peliculaService.updatePelicula(id, pelicula);
    }
    
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id)      
    {
        peliculaService.deletePelicula(id);
    }
}
