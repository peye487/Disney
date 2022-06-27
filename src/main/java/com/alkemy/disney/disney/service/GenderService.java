package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GenderDTO;
import com.alkemy.disney.disney.entity.Gender;
import com.alkemy.disney.disney.mapper.GenderMapper;
import com.alkemy.disney.disney.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService {

    @Autowired
    private GenderMapper genderMap;

    @Autowired
    private GenderRepository genderRepository;

     public GenderDTO save (GenderDTO dto){

         Gender gender = genderMap.genderDTO_entity(dto);

         Gender savedGender = genderRepository.save(gender);

         /*GenderDTO response = genderMap.entity_genderDTO(savedGender);*/

         dto = genderMap.entity_genderDTO(savedGender); // PUEDO USAR LA MISMA VARIABLE dto para retornar??

         return dto;
    }

    public List<GenderDTO>allGender(){
         List <Gender> genders = genderRepository.findAll();
         List<GenderDTO>genderDTOS = genderMap.entityList_genderDTOList(genders);

         return genderDTOS;
    }

    public void delete(String id){
         genderRepository.deleteById(id);
    }

    public GenderDTO updateGender(String id, GenderDTO dto) throws Exception {

         Optional<Gender> aswer = genderRepository.findById(id);

         if (aswer.isPresent()){
             Gender gender = aswer.get();
             gender.setName(dto.getName());
             gender.setImage(gender.getImage());

             genderRepository.save(gender);
             dto = genderMap.entity_genderDTO(gender);
             return dto;
         }else {
             throw new Exception("The character was not found");
         }
    }
}
