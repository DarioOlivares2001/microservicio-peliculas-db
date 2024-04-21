package com.peliculas.peliculas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.service.PeliculaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

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
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/peliculas")
public class PeliculaController {


    @Autowired
    private PeliculaService peliculaService;

    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

     @GetMapping
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();                    
        log.info("GET : peliculas");
        log.info("Retornando todos los estudiantes");

            List<EntityModel<Pelicula>> peliculaResources = peliculas.stream()
            .map( p -> EntityModel.of(p, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(p.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());
        
            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas());
            CollectionModel<EntityModel<Pelicula>> resources = CollectionModel.of(peliculaResources, linkTo.withRel("peliculas"));

            return resources;
    }


   /*  @GetMapping
    public List<Pelicula> getAllPeliculas() {
        
        return peliculaService.getAllPeliculas();
    }*/

    @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable Long id) {

        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        if(pelicula.isPresent())
        {
            return EntityModel.of(pelicula.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
        }
        else
        {
            throw new PeliculaNotFoundException("Peliculas no encontrada con el id: " + id);

        }
    }


    /*public ResponseEntity<?> getPeliculaById(@PathVariable Long id) {

        Optional<Pelicula> peliculas = peliculaService.getPeliculaById(id);
        if(peliculas.isPresent())
        {
            return ResponseEntity.ok(peliculas) ;    
        }else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la pelicula " + id.toString()  + "");

        }
    }*/

    @PostMapping
    public EntityModel<Pelicula> createPelicula(@RequestBody Pelicula pelicula) {
        
        Pelicula createPelicula = peliculaService.createPelicula(pelicula);
            return EntityModel.of(createPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(createPelicula.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
    }




    /*@PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        
        return peliculaService.createPelicula(pelicula);
    }*/

    @PutMapping("/{id}")
    public EntityModel<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) 
    {
        Pelicula updpelicula = peliculaService.updatePelicula(id, pelicula);
        return EntityModel.of(updpelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
    }


    /*
      @PutMapping("/{id}")
    public Pelicula updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) 
    {
        return peliculaService.updatePelicula(id, pelicula);
    }
     */
    
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id)      
    {
        peliculaService.deletePelicula(id);
    }
}
