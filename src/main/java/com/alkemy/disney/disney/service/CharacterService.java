package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    @Autowired
    CharacterMapper characterMapper;

    @Autowired
    CharacterRepository characterRepository;
    public CharacterDTO save(CharacterDTO characterDTO){

        Character character =characterMapper.characterDTO_characterEntity(characterDTO);

        Character savedCharacter = characterRepository.save(character); // Todo Por que me marca ERROR????

        characterDTO = characterMapper.characterEntity_characterDTO(savedCharacter);

        return characterDTO;

    }
}
