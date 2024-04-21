package com.peliculas.peliculas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.repository.PeliculaRepository;


@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {

    @InjectMocks
    private PeliculaServiceImpl peliculaServicio;

    @Mock
    private PeliculaRepository peliculaRepositoryMock;

    @Test
    public void guardarPeliculaTest() {

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Rocky 10");

        when(peliculaRepositoryMock.save(any())).thenReturn(pelicula);
        
        Pelicula resultado = peliculaServicio.createPelicula(pelicula);

        assertEquals("Rocky 10", resultado.getTitulo());
    }
    
}
