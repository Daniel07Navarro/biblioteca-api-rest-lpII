package com.daniel.biblioteca_lpII.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
	
	private Integer idCliente;

	@Size(min = 3,max = 30)
	@NotNull
	@NotEmpty
	private String nombreCompleto;
	
	@Size(min = 3,max = 30)
	@NotNull
	@NotEmpty
	private String apellido;
	
	@Size(min = 9,max = 9)
	@NotNull
	@NotEmpty
	private String telefono;
	
	@Size(min = 8,max = 8)
	@NotNull
	@NotEmpty
	private String dni;
	
	@Size(min = 3,max = 30)
	@NotNull
	@NotEmpty
	@Email
	private String email;
	
	@Size(min = 3,max = 30)
	@NotNull
	@NotEmpty
	private String direccion;
	
	@Size(min = 3,max = 100)
	@NotNull
	@NotEmpty
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //solamente para escritura
	private String password;

	
}
