package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.dto.MovieFilterDTO;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<Movie> getByFilters(MovieFilterDTO movieFilterDTO){

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates=new ArrayList<>();


            if (StringUtils.hasLength(movieFilterDTO.getTitle())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFilterDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }

            if (movieFilterDTO.getGender() != null){

                predicates.add(
                        criteriaBuilder.equal(root.get("genderStId"),movieFilterDTO.getGender())
                );
            }

            query.distinct(true);

            String orderByField = "title";

            query.orderBy(
                    movieFilterDTO.isASC()?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));/*Predicate[0]*/
        };

    }
}
