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

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity <Void> delete (@PathVariable String id){

        genderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity <GenderDTO> update(@PathVariable String id, @RequestBody GenderDTO genderDTO){

        try {

            GenderDTO updateGender = genderService.updateGender(id,genderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(updateGender);

        }catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
