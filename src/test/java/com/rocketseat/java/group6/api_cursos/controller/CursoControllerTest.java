package com.rocketseat.java.group6.api_cursos.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rocketseat.java.group6.api_cursos.dto.CursoResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursoUpdateResponseDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateRequestDto;
import com.rocketseat.java.group6.api_cursos.dto.CursosCreateResponseDto;
import com.rocketseat.java.group6.api_cursos.exceptions.NotFoundException;
import com.rocketseat.java.group6.api_cursos.service.CursosService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(CursosController.class)
public class CursoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CursosService cursosService;

  @InjectMocks
  private CursosController cursosController;

  CursosCreateResponseDto createCursoResponse = CursosCreateResponseDto
    .builder()
    .id(1L)
    .name("Curso de Java")
    .category("Tecnologia")
    .createdAt(LocalDate.now())
    .active(true)
    .build();;
  CursoResponseDto cursoResponseDto = CursoResponseDto.builder()
    .id(2L)
    .name("Curso RocketSeat")
    .category("Tech")
    .createdAt(LocalDate.now())
    .active(true)
    .build();

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  public void testCreateCurso() throws Exception {
    when(cursosService.create(any(CursosCreateRequestDto.class))).thenReturn(createCursoResponse);

    mockMvc.perform(post("/cursos")
      .contentType("application/json")
      .content("{\"name\":\"Curso de Java\",\"category\":\"Tecnologia\"}"))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.name").value("Curso de Java"));
  }

  @Test
  public void testGetById() throws Exception {
    Long id = 2L;

    when(cursosService.getById(id)).thenReturn(cursoResponseDto);

    mockMvc.perform(get("/cursos/{id}", id)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name").value("Curso RocketSeat"))
      .andExpect(jsonPath("$.category").value("Tech"));
  }

  @Test
  public void testGetByIdNotFound() throws Exception {
    Long id = 3L;

    when(cursosService.getById(id)).thenThrow(new NotFoundException("Curso not found"));

    mockMvc.perform(get("/cursos/{id}", id)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound());
  }

  @Test
  public void testActive() throws Exception {
    Long id = 1L;

    mockMvc.perform(patch("/cursos/{id}", id)
      .param("active", "true")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());
  }

  @Test
  public void testActiveNotFound() throws Exception {
    Long id = 1L;
    Boolean active = true;

    doThrow(new NotFoundException("Curso not Found")).when(cursosService).active(id, active);

    mockMvc.perform(patch("/cursos/{id}", id)
      .param("active", "true")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound());
  }

  @Test
  public void testUpdateCourse() throws Exception {
    Long id = 1L;

    CursoUpdateRequestDto updateRequest = CursoUpdateRequestDto
      .builder()
      .name("Curso Atualizado")
      .category("Categoria Atualizada")
      .build();

    CursoUpdateResponseDto updateResponse = CursoUpdateResponseDto
      .builder()
      .id(id)
      .name("Curso Atualizado")
      .category("Categoria Atualizada")
      .active(true)
      .build();

    // Configura o comportamento do serviço
    when(cursosService.update(id, updateRequest)).thenReturn(updateResponse);

    // Executa a requisição PUT e verifica a resposta
    mockMvc.perform(MockMvcRequestBuilders.put("/cursos/{id}", id)
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"name\":\"Curso Atualizado\",\"category\":\"Categoria Atualizada\",\"active\":true}"))
      .andExpect(status().isOk());
  }

  @Test
  public void testUpdateCourseNotFound() throws Exception {
    Long id = 2L;

    CursoUpdateRequestDto updateRequest = CursoUpdateRequestDto
      .builder()
      .name("Curso Não Encontrado")
      .category("Categoria Inexistente")
      .build();

    when(cursosService.update(any(), any())).thenThrow(new NotFoundException("Curso not found"));

    mockMvc.perform(put("/cursos/{id}", id)
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"name\":\"Curso Não Encontrado\",\"category\":\"Categoria Inexistente\",\"active\":false}"))
      .andExpect(status().isNotFound());
  }

  @Test
  public void testDelete() throws Exception {
    Long id = 1L;

    doNothing().when(cursosService).delete(id);

    mockMvc.perform(delete("/cursos/{id}", id))
      .andExpect(status().isNoContent());
  }


  @Test
  public void testDeleteNotFound() throws Exception {
    Long id = 1L;

    doThrow(new NotFoundException("Curso not Found")).when(cursosService).delete(id);

    mockMvc.perform(delete("/cursos/{id}", id))
      .andExpect(status().isNotFound());
  }

  @Test
  public void testGetAllWithFilters() throws Exception {
    Long id = 1L;

    CursoResponseDto curso1 = CursoResponseDto
      .builder()
      .name("Curso de Java")
      .category("Tecnologia")
      .build();

    CursoResponseDto curso2 = CursoResponseDto
      .builder()
      .name("Curso de Python")
      .category("Tecnologia")
      .build();

    List<CursoResponseDto> cursos = List.of(curso1, curso2);

    when(cursosService.getAllWithFilters(any(), any())).thenReturn(cursos);

    mockMvc.perform(get("/cursos", id)
      .param("category", "Tecnologia")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.[0].name").value("Curso de Java"))
      .andExpect(jsonPath("$.[0].category").value("Tecnologia"))
      .andExpect(jsonPath("$.[1].name").value("Curso de Python"))
      .andExpect(jsonPath("$.[1].category").value("Tecnologia"));;
  }


  @Test
  public void testGetAllWithFiltersNotFound() throws Exception {
    Long id = 1L;

    doThrow(NotFoundException.class).when(cursosService).getAllWithFilters(any(), any());

    mockMvc.perform(get("/cursos", id)
      .param("category", "Categoria Inválida")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound());
  }

}
