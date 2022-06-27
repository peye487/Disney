package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired @Lazy
    private MovieMapper movieMapper;

    public Character characterDTO_characterEntity(CharacterDTO dto){

        Character character = new Character();
        Movie movie = new Movie();


        character.setName(dto.getName());
        character.setImagen(dto.getImage());
        character.setAge(dto.getAge());
        character.setWeight(dto.getWeight());
        character.setHistory(dto.getHistory());

        return character;
    }

    public CharacterDTO characterEntity_characterDTO(Character savedCharacter, boolean loadMovies){

        CharacterDTO dto = new CharacterDTO();

        dto.setId(savedCharacter.getId());
        dto.setHistory(savedCharacter.getHistory());
        dto.setAge(savedCharacter.getAge());
        dto.setWeight(savedCharacter.getWeight());
        dto.setName(savedCharacter.getName());
        dto.setImage(savedCharacter.getImagen());
        if (loadMovies){
            dto.setMoviesDTO(movieMapper.entityList_movieDTOList(savedCharacter.getMovies(),false)); //VER Y REVISAR
        }
        return dto;

    }
    public List<Character>characterList(List<CharacterDTO>characterDTOList){

        List<Character>characters=new ArrayList<>();

        for (CharacterDTO c:characterDTOList) {

            characters.add(characterDTO_characterEntity(c));

        }
        return characters;
    }

    public List<CharacterDTO>EntityList_DTOList(List<Character> characterList, boolean b) {
        List<CharacterDTO> dtoList = new ArrayList<>();
        for (Character entity : characterList) {
            dtoList.add(characterEntity_characterDTO(entity, b));
        }
        return dtoList;
    }

    public CharacterBasicDTO Entity_characterBasicDTO(Character entity, boolean loadMovies){ //Todo Ver que no hace falta el boolean

        CharacterBasicDTO basicDTO = new CharacterBasicDTO();

        basicDTO.setName(entity.getName());
        basicDTO.setImage(entity.getImagen());

        return basicDTO;

    }
    public List<CharacterBasicDTO> EntityList_BasicDTOList(List<Character>characterList, boolean b){

        List<CharacterBasicDTO> basicDToList = new ArrayList<>();

        for (Character entity: characterList) {
            basicDToList.add(Entity_characterBasicDTO(entity,b));
        }
        return basicDToList;
    }
}
