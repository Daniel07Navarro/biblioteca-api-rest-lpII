package com.daniel.biblioteca_lpII.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cliente {


	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	
	@Column(nullable = false,length = 30)
	private String nombreCompleto;
	
	@Column(nullable = false,length = 30)
	private String apellido;
	
	@Column(nullable = false,length = 9)
	private String telefono;
	
	@Column(nullable = false,length = 8)
	private String dni;
	
	@Column(nullable = false,length = 30)
	private String email;
	
	@Column(nullable = false,length = 80)
	private String direccion;
	
	@Column(nullable = false,length = 100)
	private String password;

	
}
