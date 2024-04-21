package com.peliculas.peliculas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.peliculas.peliculas.model.Pelicula;




@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
    
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void guardarPeliculaTest()
    {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Avatar");

        Pelicula resultadoPelicula = peliculaRepository.save(pelicula);

        assertNotNull(resultadoPelicula.getId());
        assertEquals("Avatar", resultadoPelicula.getTitulo());
    }
}
