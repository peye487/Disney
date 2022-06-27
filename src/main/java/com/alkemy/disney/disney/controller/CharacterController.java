package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterDTO> save (@RequestBody CharacterDTO characterDTO){

        CharacterDTO savedCharacter = characterService.save(characterDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){

        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable String id, @RequestBody CharacterDTO characterDTO){
        try {
            CharacterDTO updateCharacter = characterService.updateCharacter(id,characterDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(updateCharacter);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/details")
    public ResponseEntity<List<CharacterDTO>>charactersDtos(){
        List <CharacterDTO> dtos = characterService.allCharacters();
        return ResponseEntity.ok(dtos);

    }
    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>>getByFilters(
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) Float weight,
            @RequestParam (required = false) Set<String> movies,
            @RequestParam (required = false, defaultValue = "ASC") String order){

        List<CharacterBasicDTO> characterBasicDTOS = characterService.getByFilters(name,age,weight,movies,order);
        return ResponseEntity.ok(characterBasicDTOS);
    }


}
