package com.rocketseat.java.group6.api_cursos.controller;

import com.rocketseat.java.group6.api_cursos.dto.CursoResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateResponseDto;
import com.rocketseat.java.group6.api_cursos.service.CursosService;
import com.rocketseat.java.group6.api_cursos.exceptions.NotFoundException;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursosService service;

    @Operation(summary = "Create a new course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursosCreateResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CursosCreateResponseDto> create (@Valid @RequestBody final CursosCreateRequestDto request) {
        final CursosCreateResponseDto response = service.create(request);

        return  new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    @Operation(summary = "Get a course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDto> getById(@PathVariable(name = "id") Long id) {
        try{
            final CursoResponseDto response = service.getById(id);
            return ResponseEntity.ok(response);
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Activate or deactivate a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course activation updated successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> active(@PathVariable(name = "id") Long id,
                                       @RequestParam(name = "active") Boolean active) {
        try{
            service.active(id, active);
            return ResponseEntity.noContent().build();
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoUpdateResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CursoUpdateResponseDto> update(@PathVariable(name = "id") Long id,
                                                   @Valid @RequestBody final CursoUpdateRequestDto request) {
        try{
            final CursoUpdateResponseDto response = service.update(id, request);
            return ResponseEntity.ok(response);
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Get a list of courses with optional filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Courses not found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CursoResponseDto>> getAllWithFilters(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "category", required = false) String category) {
        try{
            final List<CursoResponseDto> response = service.getAllWithFilters(name, category);
            return ResponseEntity.ok(response);
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
