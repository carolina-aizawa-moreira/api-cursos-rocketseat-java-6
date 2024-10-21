package com.rocketseat.java.group6.api_cursos.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Jacksonized
@Builder
public class CursosCreateResponseDto {
    private Long id;

    private String name;

    private String category;

    private LocalDate createdAt;

    private Boolean active;

}
