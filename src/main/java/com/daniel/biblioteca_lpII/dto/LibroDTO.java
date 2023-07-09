package com.daniel.biblioteca_lpII.dto;

import com.daniel.biblioteca_lpII.model.Libro;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.validation.constraints.Size;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibroDTO {

    private Integer idLibro;

    private String titulo;

    //@JsonIncludeProperties(value = {"nombre","pais"})
    private EditorialDTO editorial;

    //@JsonIncludeProperties(value = {"nombreArea"})
    private AreaDTO area;

    //@JsonIncludeProperties(value = {"tipo"})
    private TipoDTO tipo;

    @JsonProperty(value = "añoPublicacion")
    private String anioPublicacion;

    @Size(min = 3,max = 20)
    private String edicion;

    private String imagen;

    @Size(min = 3,max = 30)
    @NotNull
    @NotEmpty
    private String autor;

    @NotEmpty
    @NotNull
    @Size(max = 255)
    private String descripcion;

    /*
    private double precio;
    */

    private int precio;


}
