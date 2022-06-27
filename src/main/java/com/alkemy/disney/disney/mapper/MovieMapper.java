package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Gender;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired @Lazy
    CharacterMapper characterMapper;

    public Movie movieDTO_entity(MovieDTO dto){

        Movie movie = new Movie();
        Gender gender = new Gender();

        movie.setImage(dto.getImage());
        movie.setMakingDate(dto.getMakingDate());
        movie.setQualification(dto.getQualification());
        movie.setTitle(dto.getTitle());
        gender.setId(dto.getGenderId());
        movie.setGender(gender);
        movie.setGenderStId(gender.getId());
        movie.setCharacters(characterMapper.characterList(dto.getCharactersMovie()));

        return movie;

    }
    public MovieDTO entity_movieDTO(Movie movie, boolean Chart){
        MovieDTO dto = new MovieDTO();

        dto.setId(movie.getId());
        dto.setImage(movie.getImage());
        dto.setQualification(movie.getQualification());
        dto.setTitle(movie.getTitle());
        dto.setMakingDate(movie.getMakingDate());
        dto.setGenderId(movie.getGender().getId());

        dto.setCharactersMovie(characterMapper.EntityList_DTOList(movie.getCharacters(),false));


        return dto;
    }
    public List<MovieDTO> entityList_movieDTOList (List<Movie>movies, boolean b){
        List<MovieDTO> moviesDTO = new ArrayList<>();

        for (Movie m: movies) {
            moviesDTO.add(entity_movieDTO(m,b));
        }
        return moviesDTO;
    }

    public MovieBasicDTO entity_movieBasicDTO (Movie movie){
        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();

        movieBasicDTO.setImage(movie.getImage());
        movieBasicDTO.setTitle(movie.getTitle());
        movieBasicDTO.setMakingDate(movie.getMakingDate());

        return  movieBasicDTO;
    }
    public  List<MovieBasicDTO> entityList_movieBasicDTO (List<Movie>movies){

        List<MovieBasicDTO> movieBasicDTO = new ArrayList<>();

        for (Movie m : movies) {
            movieBasicDTO.add(entity_movieBasicDTO(m));
        }
        return movieBasicDTO;
    }
}
