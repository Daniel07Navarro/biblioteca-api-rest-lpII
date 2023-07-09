package com.daniel.biblioteca_lpII.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idVenta;
	
	@ManyToOne
	@JoinColumn(name = "idCliente",nullable = false,foreignKey = @ForeignKey(name = "FK_VENTA_CLIENTE"))
	private Cliente cliente;
	
	@Column(nullable = false)
	private LocalDateTime fechaVenta;
	
	@Column(columnDefinition = "decimal(6,2)",nullable = false)
	private double impuesto;
	
	@Column(columnDefinition = "decimal(6,2)",nullable = false)
	private double total;
	
	@OneToMany(mappedBy = "venta",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	List<VentaDetalle> ventaDetalles;
	

}
