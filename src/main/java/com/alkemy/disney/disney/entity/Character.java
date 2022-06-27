package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character_m")
@Getter
@Setter
@SQLDelete(sql = "UPDATE character_m SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
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

    @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY)
    private List<Movie> movies = new ArrayList<>();


   /* @Column(name = "movie_id", nullable = false)
    private String movieId;*/

    private boolean deleted = Boolean.FALSE;



}
