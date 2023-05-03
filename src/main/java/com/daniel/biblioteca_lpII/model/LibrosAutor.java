package com.daniel.biblioteca_lpII.model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LibrosAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idLibroAutor;

    @ManyToOne
    @JoinColumn(name = "idAutor",nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "idLibro",nullable = false)
    private Libro libro;
}
