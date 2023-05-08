package com.daniel.biblioteca_lpII.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.daniel.biblioteca_lpII.dto.ClienteDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Cliente;
import com.daniel.biblioteca_lpII.service.IClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	private final PasswordEncoder passwordEncoder;

	public ClienteController(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Autowired
	private IClienteService service;

	@Autowired
	@Qualifier("clienteMapper")
	private ModelMapper mapper;

	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() throws Exception {
		List<ClienteDTO> list = service.getAll().parallelStream().map(cliente -> mapper.map(cliente, ClienteDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<ClienteDTO> findOneByName(@PathVariable("nombre") String name) throws Exception{
		ClienteDTO obj = mapper.map(service.findOneByName(name),ClienteDTO.class);
		return new ResponseEntity<>(obj,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable("id") int id) throws Exception {
		ClienteDTO obj = mapper.map(service.findById(id), ClienteDTO.class);
		if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUNT  " + id);
        }
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PostMapping("/registrar")
	public ResponseEntity<ClienteDTO> save(@Valid @RequestBody  ClienteDTO clientDto) throws Exception {
		clientDto.setPassword(this.passwordEncoder.encode(clientDto.getPassword())); //PARA ENCRIPTAR LA CONTRASEÑA
		Cliente obj = service.save(mapper.map(clientDto, Cliente.class));
		return new ResponseEntity<>(mapper.map(obj, ClienteDTO.class), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ClienteDTO> update(@Valid @RequestBody  ClienteDTO clientDto) throws Exception {
		Cliente obj = service.save(mapper.map(clientDto, Cliente.class));
		return new ResponseEntity<>(mapper.map(obj, ClienteDTO.class), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/id")
	public ResponseEntity<Void> deleteById(@RequestParam int id) throws Exception{
		Cliente obj = service.findById(id);
		if(obj == null) {
			throw new ModelNotFoundException("ID NOT FOUND "+id);
		}
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
	

}
