package com.rocketseat.java.group6.api_cursos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@Builder
@AllArgsConstructor
public class CursosCreateRequestDto {
    @NotEmpty
    @Schema(description = "Name of the course. Must not be empty.", example = "Java Programming")
    private String name;

    @NotEmpty
    @Schema(description = "Category of the course. Must not be empty.", example = "Programming")
    private String category;

}
