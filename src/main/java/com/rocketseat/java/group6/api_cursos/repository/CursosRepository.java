package com.rocketseat.java.group6.api_cursos.repository;

import com.rocketseat.java.group6.api_cursos.persistence.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursosRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByNameOrCategory(String name, String category);

    @Query("""
            select c from Curso c 
            where (:name is null or c.name = :name) 
            and (:category is null or c.category = :category) 
            """)
    List<Curso> getAllByParams(@Param("name") String name, 
                                @Param("category") String category);
}
