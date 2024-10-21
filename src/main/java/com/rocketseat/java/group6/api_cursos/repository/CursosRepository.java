package com.rocketseat.java.group6.api_cursos.repository;

import com.rocketseat.java.group6.api_cursos.persistence.Curso;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursosRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByNameOrCategory(String name, String category);
}
