package com.daniel.biblioteca_lpII.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.daniel.biblioteca_lpII.security.JwtTokenUtil;
import com.daniel.biblioteca_lpII.service.IVentaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.apache.coyote.Response;
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
	private IVentaService serviceVenta;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

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

	@GetMapping("/reporte")
	public ResponseEntity<Map<String,Double>> clienteConMasCompras() throws Exception{
		return new ResponseEntity<>(serviceVenta.clienteConMasCompras(),HttpStatus.OK);
	}

	@GetMapping("/detalles")
	public ResponseEntity<List<Map<String,Object>>> verMisDetalles(@RequestParam("id") Integer id)throws Exception{
		List<Map<String,Object>> listaObj = service.misDetalles(id);

		if(listaObj.isEmpty()){
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(service.misDetalles(id),HttpStatus.OK);
	}

	@GetMapping("/detallesV2")
	@Operation(summary = "VER MIS DETALLES",description = "TRAE UN REPORTE DE DETALLES DE UN RESPECTIVO CLIENTE")
	public ResponseEntity<List<Map<String,Object>>> verMisDetallesV2(@RequestParam("id") Integer id) throws Exception{
		List<Map<String,Object>> listaDetalles = service.misDetallesV2(id);
		if(listaDetalles.isEmpty()){
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listaDetalles,HttpStatus.OK);
	}

	//DETEALLES V3
	@GetMapping("/miCliente")
	public ResponseEntity<?> verificarMisDetalles(HttpServletRequest request) throws Exception{
		if(request.getHeader("Authorization")==null){
			return new ResponseEntity<>(Map.of("Error", "No esta presente el header"),HttpStatus.BAD_REQUEST);
		}
		String token = jwtTokenUtil.getToken(request);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		Cliente cliente = service.findOneByName(username);
		List<Map<String,Object>> detalles = service.misDetallesV2(cliente.getIdCliente());
		return new ResponseEntity<>(detalles,HttpStatus.OK);
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
