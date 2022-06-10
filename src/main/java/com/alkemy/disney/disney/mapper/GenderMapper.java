package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenderDTO;
import com.alkemy.disney.disney.entity.Gender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenderMapper {
    public Gender genderDTO_entity(GenderDTO dto){

        Gender gender = new Gender();
        gender.setImage(dto.getImage());
        gender.setName(dto.getName());

        return gender;

    }

    public GenderDTO entity_genderDTO(Gender gender){

        GenderDTO dto = new GenderDTO();

        dto.setId(gender.getId());
        dto.setName(gender.getName());
        dto.setImage(gender.getImage());

        return dto;
    }

public List<GenderDTO> entityList_genderDTOList (List<Gender>genders){

        List<GenderDTO>dtos =new ArrayList<>();
    for (Gender g:genders) {
        dtos.add(entity_genderDTO(g));
    }
    return dtos;
}
}
