package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CharacterFilterDTO {

    private String name;
    private Integer age;
    private Float weight;
    private Set<String> movies;
    private String order;

    public CharacterFilterDTO (String name, Integer age, Float weight, Set<String>movies,String order){
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
        this.order = order;
    }

    public boolean isASC(){return this.order.compareToIgnoreCase("ASC")==0;}
    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC")==0;}

}
