package com.rocketseat.java.group6.api_cursos.dto;

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
    private String name;

    @NotEmpty
    private String category;

}
