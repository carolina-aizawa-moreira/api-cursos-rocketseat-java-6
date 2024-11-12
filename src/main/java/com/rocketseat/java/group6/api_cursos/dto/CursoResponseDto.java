package com.rocketseat.java.group6.api_cursos.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CursoResponseDto {
    @Schema(description = "Unique identifier of the course", example = "1")
    private Long id;

    @Schema(description = "Name of the course", example = "Java Programming")
    private String name;

    @Schema(description = "Category of the course", example = "Programming")
    private String category;

    @Schema(description = "Date when the course was created", example = "2023-01-01")
    private LocalDate createdAt;

    @Schema(description = "Indicates if the course is active", example = "true")
    private Boolean active;
}
