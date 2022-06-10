package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Character;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterMapper {

    public Character characterDTO_characterEntity(CharacterDTO dto){

        Character character = new Character();

        character.setName(dto.getName());
        character.setImagen(dto.getImagen());
        character.setAge(dto.getAge());
        character.setWeight(dto.getWeight());

        return character;
    }

    public CharacterDTO characterEntity_characterDTO(Character savedCharacter, boolean loadMovies){

        CharacterDTO dto = new CharacterDTO();

        dto.setId(savedCharacter.getId());
        dto.setHistory(savedCharacter.getHistory());
        dto.setAge(savedCharacter.getAge());
        dto.setWeight(savedCharacter.getWeight());
        dto.setName(savedCharacter.getName());
        dto.setImagen(savedCharacter.getImagen());
        if (loadMovies){        // Todo VER BIEN COMO TRABAJAR PARA SETEAR LAS PELICULAS RELACIONADAS CON ÉSTE if (Como envío el boolean?)
            List<MovieDTO>moviesDTO = movieEntityList_movieDTOList(savedCharacter.getMovies()); //Todo ARMAR EL Método "movieEntityList_movieDTOList" en MovieMapper Y Hacer AutoWired de MovieMapper
            dto.setMoviesDTO(moviesDTO); //VER Y REVISAR


        }


    }
}
