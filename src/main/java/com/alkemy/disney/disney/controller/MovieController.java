package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> saved(@RequestBody MovieDTO movieDTO){

        MovieDTO savedMovie = movieService.saved(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);

    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity <Void> delete (@PathVariable String id){

        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<MovieDTO>update(@PathVariable String id, @RequestBody MovieDTO movieDTO){

        try {
            MovieDTO updateMovie = movieService.update(id,movieDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(updateMovie);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/details")
    public ResponseEntity<List<MovieDTO>>moviesdto(){

        List<MovieDTO>movieDTOS = movieService.allMovies();
        return ResponseEntity.ok(movieDTOS);
    }
    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getByFilters(
            @RequestParam (required = false) String title,
            @RequestParam (required = false) String gender,
            @RequestParam (required = false, defaultValue = "ASC") String order
    ){
        List<MovieBasicDTO> movieBasicDTOS = movieService.getByFilters(title,gender,order);
        return ResponseEntity.ok(movieBasicDTOS);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<MovieDTO>addCharacter(@PathVariable String idMovie,@PathVariable String idCharacter){

        try {
            MovieDTO addCharacterToMovieDTO = movieService.addCharacter(idMovie,idCharacter);
            return ResponseEntity.status(HttpStatus.CREATED).body(addCharacterToMovieDTO);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void>deleteCharacterToMovie(@PathVariable String idMovie, @PathVariable String idCharacter){

        try {
            movieService.deleteCharacterToMovie(idMovie, idCharacter);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
