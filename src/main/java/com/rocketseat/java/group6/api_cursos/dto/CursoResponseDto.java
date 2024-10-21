package com.rocketseat.java.group6.api_cursos.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CursoResponseDto {
    private Long id;

    private String name;

    private String category;

    private LocalDate createdAt;

    private Boolean active;
}
