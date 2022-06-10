package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.GenderDTO;
import com.alkemy.disney.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gender")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @PostMapping
    public ResponseEntity<GenderDTO> save (@RequestBody GenderDTO genderDTO){

        GenderDTO savedGender = genderService.save(genderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGender);

    }
    @GetMapping
    public ResponseEntity<List<GenderDTO>>listAll(){
        List <GenderDTO> genders = genderService.allGender();

        return ResponseEntity.ok().body(genders);
    }

}
