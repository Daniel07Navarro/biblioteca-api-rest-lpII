package com.daniel.biblioteca_lpII.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VentaDTO {

    private Integer idVenta;
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ClienteDTO cliente;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //para que solo sea lectura en el response
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime fechaVenta = LocalDateTime.now();

    private double impuesto;

    private double total;

    @JsonManagedReference //LA CLASE PADRE PARA EVITAR EL CILCLO
    private List<VentaDetalleDTO> ventaDetalles;

}
