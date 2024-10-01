package com.rocketseat.java.group6.api_cursos.persistence;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "Cursos")
@Getter
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "category", length = 100, nullable = false)
    private String category;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(value = TemporalType.DATE)
    private Date updatedAt;

}
