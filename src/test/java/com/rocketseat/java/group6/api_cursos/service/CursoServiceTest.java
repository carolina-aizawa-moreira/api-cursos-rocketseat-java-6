package com.rocketseat.java.group6.api_cursos.service;


import static com.rocketseat.java.group6.api_cursos.mapper.CursosMapper.toModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rocketseat.java.group6.api_cursos.dto.CursoResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateResponseDto;
import com.rocketseat.java.group6.api_cursos.exceptions.NotFoundException;
import com.rocketseat.java.group6.api_cursos.mapper.CursosMapper;
import com.rocketseat.java.group6.api_cursos.persistence.Curso;
import com.rocketseat.java.group6.api_cursos.repository.CursosRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CursoServiceTest {

  @InjectMocks
  private CursosService cursosService;

  @Mock
  private CursosRepository cursosRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreate() {
    CursosCreateRequestDto requestDto = CursosCreateRequestDto
      .builder()
      .name("Curso Java")
      .category("Programação")
      .build();

    Curso curso = new Curso();
    curso.setId(1L);
    curso.setName(requestDto.getName());
    curso.setCategory(requestDto.getCategory());

    when(cursosRepository.save(any())).thenReturn(curso);

    CursosCreateResponseDto responseDto = cursosService.create(requestDto);

    assertEquals(curso.getName(), responseDto.getName());
    assertEquals(curso.getCategory(), responseDto.getCategory());
  }

  @Test
  public void testGetById_CursoFound() {
    Long id = 1L;
    Curso curso = new Curso();
    curso.setId(id);
    curso.setName("Curso Java");
    curso.setCategory("Programação");

    when(cursosRepository.getReferenceById(id)).thenReturn(curso);

    CursoResponseDto responseDto = cursosService.getById(id);

    assertEquals(curso.getName(), responseDto.getName());
    assertEquals(curso.getCategory(), responseDto.getCategory());
  }

  @Test
  public void testGetById_CursoNotFound() {
    Long id = 1L;

    when(cursosRepository.getReferenceById(id)).thenThrow(new NotFoundException("Curso not found"));

    assertThrows(NotFoundException.class, () -> cursosService.getById(id));
  }

  @Test
  public void testActive_CursoFound() {
    Long id = 1L;
    Boolean active = false;

    Curso curso = new Curso();
    curso.setId(id);
    curso.setActive(true);

    when(cursosRepository.getReferenceById(id)).thenReturn(curso);

    cursosService.active(id, active);

    assertFalse(curso.getActive());
    verify(cursosRepository).save(curso);
  }

  @Test
  public void testActive_CursoNotFound() {
    Long id = 2L;

    when(cursosRepository.getReferenceById(id)).thenThrow(new NotFoundException("Curso not found"));

    assertThrows(NotFoundException.class, () -> cursosService.active(id, true));
  }

  @Test
  public void testDelete_CursoExists() {
    Long id = 1L;

    cursosService.delete(id);

    verify(cursosRepository).deleteById(id);
  }

  @Test
  public void testUpdate_CursoFound() {
    Long id = 1L;
    CursoUpdateRequestDto requestDto = CursoUpdateRequestDto
      .builder()
      .name("Curso Java Atualizado")
      .category("Programação")
      .build();

    Curso curso = new Curso();
    curso.setId(id);
    curso.setName("Curso Java");
    curso.setCategory("Programação");

    when(cursosRepository.getReferenceById(id)).thenReturn(curso);
    when(cursosRepository.save(toModel(curso, requestDto))).thenReturn(curso);

    CursoUpdateResponseDto responseDto = cursosService.update(id, requestDto);

    assertEquals("Curso Java Atualizado", responseDto.getName());
    assertEquals("Programação", responseDto.getCategory());
  }

  @Test
  public void testUpdate_CursoNotFound() {
    Long id = 1L;
    CursoUpdateRequestDto requestDto = CursoUpdateRequestDto.builder().build();

    when(cursosRepository.getReferenceById(id)).thenThrow(new NotFoundException("Curso not found"));

    assertThrows(NotFoundException.class, () -> cursosService.update(id, requestDto));
  }

  @Test
  public void testGetAllWithFilters() {
    // Dados de exemplo
    Curso curso1 = new Curso("Curso Java", "Programação");
    Curso curso2 = new Curso("Curso Spring", "Framework");

    when(cursosRepository.getAllByParams(null, null)).thenReturn(Arrays.asList(curso1, curso2));

    List<CursoResponseDto> responseDtos = cursosService.getAllWithFilters(null, null);

    assertEquals(2, responseDtos.size());
    assertEquals("Curso Java", responseDtos.get(0).getName());
    assertEquals("Curso Spring", responseDtos.get(1).getName());
  }

  @Test
  public void testValidateNotFound() {
    Curso curso = new Curso();

    when(cursosRepository.getReferenceById(any())).thenReturn(curso);

    assertThrows(NotFoundException.class, () -> cursosService.getById(any()));
  }
}