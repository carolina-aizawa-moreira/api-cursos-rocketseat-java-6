package com.rocketseat.java.group6.api_cursos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CursoUpdateRequestDto {

    @Schema(description = "Name of the course. Must not be empty.", example = "Java Programming")
    private final String name;

    @Schema(description = "Category of the course. Must not be empty.", example = "Programming")
    private final String category;
}
