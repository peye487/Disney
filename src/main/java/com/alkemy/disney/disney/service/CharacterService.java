package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFilterDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.CharacterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterService {

    @Autowired
    CharacterMapper characterMapper;
    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    CharacterSpecification characterSpecification;

    public CharacterDTO save(CharacterDTO characterDTO){

        Character character = characterMapper.characterDTO_characterEntity(characterDTO);

        Character savedCharacter = characterRepository.save(character); // Todo Por que me marca ERROR????

        characterDTO = characterMapper.characterEntity_characterDTO(savedCharacter, false);

        return characterDTO;

    }

    public CharacterDTO updateCharacter(String id, CharacterDTO dto) throws Exception {

        Optional<Character> answer = characterRepository.findById(id);

        if (answer.isPresent()){

            Character character = answer.get();
            character.setName(dto.getName());
            character.setImagen(dto.getImage());
            character.setHistory(dto.getHistory());
            character.setAge(dto.getAge());
            character.setWeight(dto.getWeight());

            characterRepository.save(character);

            dto = characterMapper.characterEntity_characterDTO(character,false);
            return dto;
        }else {
            throw new Exception("The character was not found");
        }

    }
    public void delete(String id){

        System.out.println("ENTRO A SERVICIO CHARACTER");
        System.out.println(id);
        System.out.println("----------------------------");
        characterRepository.deleteById(id);
        System.out.println("SALIDA DE REPOSITORIO DELETE");
    }

    public List<CharacterDTO> allCharacters (){

        List <Character> characters = characterRepository.findAll();
        List<CharacterDTO>characterDTOS = characterMapper.EntityList_DTOList(characters,true);
        return characterDTOS;
    }

    public List<CharacterBasicDTO>getByFilters(String name, Integer age, Float weight, Set<String> movies, String order){

        CharacterFilterDTO characterFilterDTO = new CharacterFilterDTO(name,age,weight,movies,order);

        List<Character>characters=characterRepository.findAll(characterSpecification.getByFilters(characterFilterDTO));

        List<CharacterBasicDTO>characterBasicDTOS = characterMapper.EntityList_BasicDTOList(characters,false);

        return characterBasicDTOS;
    }

}
