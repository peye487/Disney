package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.dto.CharacterFilterDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class CharacterSpecification {

    public Specification<Character>getByFilters(CharacterFilterDTO characterFilterDTO){

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates=new ArrayList<>();

            System.out.println(characterFilterDTO.getAge() + "------" + characterFilterDTO.getName() +"------"+characterFilterDTO.getMovies());

            if (StringUtils.hasLength(characterFilterDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + characterFilterDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (characterFilterDTO.getAge()!=null){

                predicates.add(
                        criteriaBuilder.equal(root.get("age"),characterFilterDTO.getAge())
                        );

            }
            if (!CollectionUtils.isEmpty(characterFilterDTO.getMovies())){
                Join<Movie, Character> join =root.join("movies", JoinType.INNER);
                Expression<String>moviesId = join.get("id");
                predicates.add(moviesId.in(characterFilterDTO.getMovies()));



            }
            query.distinct(true);

            String orderByField = "name";

            query.orderBy(
                    characterFilterDTO.isASC()?
                    criteriaBuilder.asc(root.get(orderByField)):
                    criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));/*Predicate[0]*/
        };

    }
}
