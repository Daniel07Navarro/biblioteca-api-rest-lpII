package com.daniel.biblioteca_lpII.dto;

import com.fasterxml.jackson.annotation.*;
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
public class VentaDetalleDTO {

    private Integer idVentaDetalle;

    @JsonBackReference
    private VentaDTO venta;

    //@JsonIncludeProperties(value = {"idLibro","titulo","editorial","precio"})
    private LibroDTO libro;

    @NotNull
    @NotEmpty
    private short cantidad;

    @NotNull
    @NotEmpty
    private double precio;


}
