package com.peliculas.peliculas.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.repository.PeliculaRepository;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> getAllPeliculas()
    {
        return peliculaRepository.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Long id)
    {
        return peliculaRepository.findById(id);
    }

    @Override
    public Pelicula createPelicula(Pelicula pelicula)
    {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public void deletePelicula(Long id)
    {
        peliculaRepository.deleteById(id);
    }

    @Override
    public Pelicula updatePelicula(long id, Pelicula pelicula)
    {
        if(peliculaRepository.existsById(id))
        {
            pelicula.setId(id);
            return peliculaRepository.save(pelicula);
        }
        else
        {
            return null;
        }

    }

    
    
}
