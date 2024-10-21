package com.rocketseat.java.group6.api_cursos.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CursoUpdateRequestDto {

    private final String name;

    private final String category;
}
