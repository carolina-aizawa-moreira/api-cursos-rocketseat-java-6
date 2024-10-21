package com.rocketseat.java.group6.api_cursos.service;

import static com.rocketseat.java.group6.api_cursos.mapper.CursosMapper.toCreateResponseDto;
import static com.rocketseat.java.group6.api_cursos.mapper.CursosMapper.toCursoResponseDto;
import static com.rocketseat.java.group6.api_cursos.mapper.CursosMapper.toCursoUpdateResponseDto;
import static com.rocketseat.java.group6.api_cursos.mapper.CursosMapper.toModel;

import com.rocketseat.java.group6.api_cursos.dto.CursoResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateResponseDto;
import com.rocketseat.java.group6.api_cursos.persistence.Curso;
import com.rocketseat.java.group6.api_cursos.repository.CursosRepository;


import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursosService {

    @Autowired
    private CursosRepository repository;

    public CursosCreateResponseDto create(final CursosCreateRequestDto request) {
        final Curso cursos = repository.save(toModel(request));

        return toCreateResponseDto(cursos);
    }

    public CursoResponseDto getById(final Long id) {
        final Curso curso = repository.getReferenceById(id);
        this.validate(curso);

        return toCursoResponseDto(curso);
    }

    public void active(final Long id,final Boolean active) {
        final Curso curso = repository.getReferenceById(id);
        curso.setActive(active);
        final Curso updatedCurso = repository.save(curso);
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }

    public CursoUpdateResponseDto update(final Long id, final CursoUpdateRequestDto request) {
        final Curso curso = repository.getReferenceById(id);
        this.validate(curso);

        final Curso updatedCurso = repository.save(toModel(curso, request));

        return toCursoUpdateResponseDto(updatedCurso);
    }

    private void validate(final Curso curso) {
        if(Objects.isNull(curso.getId())) {
            throw new RuntimeException("Curso not found");
        }
    }

    public List<CursoResponseDto> getAllWithFilters(final String name, final String category) {
        final List<Curso> cursos = repository.getAllByParams(name, category);

        List<CursoResponseDto> response = cursos.stream().map(curso -> toCursoResponseDto(curso)).toList();

        return response;
    }


}
