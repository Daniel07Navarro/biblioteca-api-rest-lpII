package com.daniel.biblioteca_lpII.model;

import java.time.LocalDateTime;

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
public class Libro {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLibro;
	
	@Column(nullable = false,length = 30)
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name = "idEditorial",nullable = false)
	private Editorial editorial;
	
	@ManyToOne
	@JoinColumn(name = "idArea",nullable = false)
	private Area area;
	
	@ManyToOne
	@JoinColumn(name = "idTipo",nullable = false)
	private Tipo tipo;
	
	@Column(name = "año_publicacion",nullable = false)
	private String anioPublicacion;
	
	@Column(nullable = false,length = 20)
	private String edicion;
	
	@Column(nullable = false,length = 20)
	private String ISBN;
	
	@Column(nullable = false,length = 10)
	private String estado;

	private String imagen;
	

}
