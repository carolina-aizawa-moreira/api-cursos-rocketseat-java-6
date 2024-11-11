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


@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursosService service;

    @PostMapping
    public ResponseEntity<CursosCreateResponseDto> create (@Valid @RequestBody final CursosCreateRequestDto request) {
        final CursosCreateResponseDto response = service.create(request);

        return  new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDto> getById(@PathVariable(name = "id") Long id) {
        try{
            final CursoResponseDto response = service.getById(id);
            return ResponseEntity.ok(response);
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

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
