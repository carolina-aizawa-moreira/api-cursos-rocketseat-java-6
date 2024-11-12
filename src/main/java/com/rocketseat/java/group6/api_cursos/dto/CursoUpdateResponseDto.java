package com.rocketseat.java.group6.api_cursos.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CursoUpdateResponseDto {

    @Schema(description = "Unique identifier of the course", example = "5")
    private Long id;

    @Schema(description = "Name of the course", example = "Advanced Java Programming")
    private String name;

    @Schema(description = "Category of the course", example = "Programming")
    private String category;

    @Schema(description = "Date when the course was originally created", example = "2023-01-01")
    private LocalDate createdAt;

    @Schema(description = "Indicates if the course is currently active", example = "true")
    private Boolean active;

    @Schema(description = "Date when the course was last updated", example = "2024-12-10")
    private LocalDate updatedAt;
}
