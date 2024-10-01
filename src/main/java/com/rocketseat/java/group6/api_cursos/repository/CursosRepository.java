package com.rocketseat.java.group6.api_cursos.repository;

import com.rocketseat.java.group6.api_cursos.persistence.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long> {
}
