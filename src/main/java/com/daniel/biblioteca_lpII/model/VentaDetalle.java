package com.daniel.biblioteca_lpII.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VentaDetalle {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVentaDetalle;
	
	@ManyToOne
	@JoinColumn(name = "idVenta")
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "idLibro")
	private Libro libro;
	
	@Column(nullable = false)
	private short cantidad;
	
	@Column(columnDefinition = "decimal(6,2)",nullable = false)
	private double precio;

}
