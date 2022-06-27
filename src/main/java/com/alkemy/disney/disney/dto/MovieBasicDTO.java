package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovieBasicDTO {

    private String title;
    private String image;
    private Date makingDate;
}
