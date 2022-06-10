package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> saved(@RequestBody MovieDTO movieDTO){

        MovieDTO savedMovie = movieService.saved(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);

    }

}
