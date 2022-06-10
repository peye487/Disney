package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieRepository movieRepository;

    public MovieDTO saved (MovieDTO movieDTO){

        Movie movie = movieMapper.movieDTO_entity(movieDTO);

        Movie savedMovie = movieRepository.save(movie);

        // Todo TENGO QUE HACER EL PASO INVERSO PARA PASAR DE ENTITY A DTO CON TODOS LOS DATOS INCLUIDO "Id"
        // Todo PARA RETORNAR A CONTROLADOR.

    }
}
