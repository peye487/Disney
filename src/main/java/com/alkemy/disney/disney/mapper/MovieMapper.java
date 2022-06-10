package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie movieDTO_entity(MovieDTO dto){

        Movie movie = new Movie();
        movie.setImage(dto.getImage());
        movie.setMakingDate(dto.getMakingDate());
        movie.setQualification(dto.getQualification());
        movie.setTitle(dto.getTitle());

        return movie;

    }

    // Todo Hacer el método Entity a DTO para retornar del servicio a Controlador.
    // Todo VER COMO ES EL TEMA PARA SETEAR LOS PERSONAJES RELACIONADOS
}
