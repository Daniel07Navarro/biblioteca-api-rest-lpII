package com.daniel.biblioteca_lpII.dto;


import com.daniel.biblioteca_lpII.model.Pais;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditorialDTO {

    private Integer idEditorial;

    @NotNull
    @NotEmpty
    @Size(min = 3,max = 30)
    private String nombre;

    //@JsonIncludeProperties(value = {"pais"})
    private PaisDTO pais;


}
