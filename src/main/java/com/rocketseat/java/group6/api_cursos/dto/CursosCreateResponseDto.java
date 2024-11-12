package com.rocketseat.java.group6.api_cursos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Jacksonized
@Builder
public class CursosCreateResponseDto {
    @Schema(description = "Unique identifier of the course", example = "2")
    private Long id;

    @Schema(description = "Name of the course", example = "Java Programming")
    private String name;

    @Schema(description = "Category of the course", example = "Programming")
    private String category;

    @Schema(description = "Date when the course was created", example = "2023-01-01")
    private LocalDate createdAt;

    @Schema(description = "Indicates if the course is active", example = "false")
    private Boolean active;

}
