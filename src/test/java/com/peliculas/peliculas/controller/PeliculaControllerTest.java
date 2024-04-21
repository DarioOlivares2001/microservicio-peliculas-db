package com.peliculas.peliculas.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.service.PeliculaService;


@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService peliculaServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception{
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Predator");
        pelicula1.setId(1L);

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Breakdown");
        pelicula2.setId(2L);

        List<Pelicula> peliculas = List.of(pelicula1, pelicula2); 

        List<EntityModel<Pelicula>> peliculaResources = peliculas.stream()
            .map(pelicula -> EntityModel.of(pelicula))
            .collect(Collectors.toList());

        when(peliculaServiceMock.getAllPeliculas()).thenReturn(peliculas);

        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.peliculas.length()").value(2))
                .andExpect(jsonPath("$._embedded.peliculas[0].titulo").value("Predator"))
                .andExpect(jsonPath("$._embedded.peliculas[1].titulo").value("Breakdown"))
                .andExpect(jsonPath("$._embedded.peliculas[0]._links.self.href").value("http://localhost:8093/peliculas/1"))
                .andExpect(jsonPath("$._embedded.peliculas[1]._links.self.href").value("http://localhost:8093/peliculas/2"));

    }


}
