package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character")
@Getter
@Setter
public class Character {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String imagen;
    private String name;
    private Integer age;
    private Float weight;
    private String history;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();


    @Column(name = "movie_id", nullable = false)
    private String movieId;




}
