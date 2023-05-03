package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.LibroDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.service.ILibroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    @Autowired
    private ILibroService service;

    @Autowired
    @Qualifier("tipoMapper")
    private ModelMapper mapper;

    @GetMapping("/mostrar")
    private ResponseEntity<List<LibroDTO>> findAll() throws Exception {
        List<LibroDTO> list = service.getAll()
                .parallelStream()
                .map(lib -> mapper.map(lib, LibroDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> findById(@PathVariable("id") int id) throws Exception {
        LibroDTO obj = mapper.map(service.findById(id), LibroDTO.class);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUNT  " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LibroDTO> save(@Valid @RequestBody LibroDTO clientDto) throws Exception {
        Libro obj = service.save(mapper.map(clientDto, Libro.class));
        return new ResponseEntity<>(mapper.map(obj, LibroDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LibroDTO> update(@Valid @RequestBody LibroDTO clientDto) throws Exception {
        Libro obj = service.save(mapper.map(clientDto, Libro.class));
        return new ResponseEntity<>(mapper.map(obj, LibroDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws Exception {
        Libro obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
