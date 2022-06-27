package com.alkemy.disney.disney.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.disney.disney.entity.Character;

import java.util.List;


@Repository
public interface CharacterRepository extends JpaRepository<Character,String> {

    List<Character> findAll(Specification<Character>spec);
}
