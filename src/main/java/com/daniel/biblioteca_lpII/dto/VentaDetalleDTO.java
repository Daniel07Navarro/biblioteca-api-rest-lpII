package com.daniel.biblioteca_lpII.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VentaDetalleDTO {

    private Integer idVentaDetalle;

    @JsonBackReference
    private VentaDTO venta;

    @JsonIncludeProperties(value = {"titulo","editorial"})
    private LibroDTO libro;

    @NotNull
    @NotEmpty
    private short cantidad;

    @NotNull
    @NotEmpty
    private double precio;


}
