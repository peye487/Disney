package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private String id;

    private String image;
    private String title;
    private Date makingDate;
    private Integer qualification;
    private String genderId;
    private List<CharacterDTO>charactersMovie;
}
