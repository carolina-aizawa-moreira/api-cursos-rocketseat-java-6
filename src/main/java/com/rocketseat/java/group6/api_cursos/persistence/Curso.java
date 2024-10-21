package com.rocketseat.java.group6.api_cursos.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Cursos")
@Getter
@Setter
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, unique = true)
    private String name;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    public Curso(final String name, final String category) {
        this.name = name;
        this.category = category;
        this.active = true;
        this.createdAt = LocalDate.now();
    }

}
