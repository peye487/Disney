package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovieDTO {

    private String id;

    private String image;
    private String title;
    private Date makingDate;
    private Integer qualification;
    //VER COMO HAGO RELACION CON CHARACTER
}
