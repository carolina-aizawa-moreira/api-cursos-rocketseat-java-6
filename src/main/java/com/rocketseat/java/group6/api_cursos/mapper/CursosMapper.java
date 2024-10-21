package com.rocketseat.java.group6.api_cursos.mapper;

import com.rocketseat.java.group6.api_cursos.dto.CursoResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateResponseDto;
import com.rocketseat.java.group6.api_cursos.persistence.Curso;
import java.time.LocalDate;

public interface CursosMapper {

    static Curso toModel(final CursosCreateRequestDto request) {
        return new Curso(request.getName(), request.getCategory());
    }

    static Curso toModel(final Curso curso, final CursoUpdateRequestDto request) {
        curso.setName(request.getName());
        curso.setCategory(request.getCategory());
        curso.setUpdatedAt(LocalDate.now());

        return curso;
    }

    static CursosCreateResponseDto toCreateResponseDto(final Curso cursos) {
        return CursosCreateResponseDto.builder()
                .id(cursos.getId())
                .name(cursos.getName())
                .category(cursos.getCategory())
                .createdAt(cursos.getCreatedAt())
                .active(cursos.getActive())
                .build();
    }

    static CursoResponseDto toCursoResponseDto(final Curso curso) {
        return CursoResponseDto.builder()
                .id(curso.getId())
                .name(curso.getName())
                .category(curso.getCategory())
                .createdAt(curso.getCreatedAt())
                .active(curso.getActive())
                .build();
    }

    static CursoUpdateResponseDto toCursoUpdateResponseDto(final Curso updatedCurso) {
        return CursoUpdateResponseDto.builder()
                .id(updatedCurso.getId())
                .name(updatedCurso.getName())
                .category(updatedCurso.getCategory())
                .createdAt(updatedCurso.getCreatedAt())
                .active(updatedCurso.getActive())
                .updatedAt(updatedCurso.getUpdatedAt())
                .build();
    }
}
