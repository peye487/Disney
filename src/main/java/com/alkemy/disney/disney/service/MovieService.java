package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFilterDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.entity.Gender;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.MovieSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieSpecification movieSpecification;

    @Autowired
    CharacterService characterService;

    @Autowired
    CharacterRepository characterRepository;

    public MovieDTO saved (MovieDTO movieDTO){

        Movie movie = movieMapper.movieDTO_entity(movieDTO);

        Movie savedMovie = movieRepository.save(movie);

        movieDTO = movieMapper.entity_movieDTO(savedMovie,false);

        return movieDTO;
    }

    public MovieDTO update (String id, MovieDTO movieDTO) throws Exception {

        Optional<Movie>aswer = movieRepository.findById(id);

        if (aswer.isPresent()){
            Movie movie = aswer.get();

            movie.setImage(movieDTO.getImage());
            movie.setTitle(movieDTO.getTitle());
            movie.setQualification(movieDTO.getQualification());
            movie.setMakingDate(movieDTO.getMakingDate());

            if (movieDTO.getGenderId()!=null){
                Gender gender = new Gender();
                gender.setId(movieDTO.getGenderId());
                movie.setGender(gender);
                movie.setGenderStId(gender.getId());
            }

            movieRepository.save(movie);

            movieDTO = movieMapper.entity_movieDTO(movie,false);
            return movieDTO;
        }else {
            throw new Exception("The Movie was not found");
        }
    }
    public void delete(String id){
        movieRepository.deleteById(id);
    }

    public List<MovieDTO>allMovies(){
        List<Movie>movies = movieRepository.findAll();

        List<MovieDTO>movieDTOS = movieMapper.entityList_movieDTOList(movies,true);

        return movieDTOS;
    }
    public List<MovieBasicDTO> getByFilters (String title, String gender, String order){

        MovieFilterDTO movieFilterDTO = new MovieFilterDTO(title,gender,order);

        List<Movie> movies = movieRepository.findAll(movieSpecification.getByFilters(movieFilterDTO)); //Todo POR QUE ME PIDE castear a SORT??

        List <MovieBasicDTO> movieBasicDTO = movieMapper.entityList_movieBasicDTO(movies);

        return movieBasicDTO;
    }

    public MovieDTO addCharacter(String idMovie, String idCharacter) throws Exception {

        Optional<Movie> aswer = movieRepository.findById(idMovie);

        Optional<Character>aswerCharacter = characterRepository.findById(idCharacter);


        if (aswer.isPresent() && aswerCharacter.isPresent()){
            Movie movie = aswer.get();
            Character character = aswerCharacter.get();

            movie.getCharacters().add(character);

            movieRepository.save(movie);

            MovieDTO movieDTO = movieMapper.entity_movieDTO(movie,true);

            return movieDTO;
        }else {
            throw new Exception("The Movie or character was not found");
        }
    }
    public void deleteCharacterToMovie(String idMovie, String idCharacter) throws Exception {
        Optional<Movie>answer= movieRepository.findById(idMovie);

        if (answer.isPresent()){
            Movie movie = answer.get();

            for (Character c: movie.getCharacters()) {
                if (c.getId().equals(idCharacter)){

                    movie.getCharacters().remove(c);
                    movieRepository.save(movie);
                }
            }
        }else {
            throw new Exception("The Movie was not found");
        }
    }
}

