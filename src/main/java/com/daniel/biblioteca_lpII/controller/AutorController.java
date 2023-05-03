package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.AutorDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Autor;
import com.daniel.biblioteca_lpII.service.IAutorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/autores")
//@CrossOrigin(origins = "*")
public class AutorController {

    @Autowired
    private IAutorService service;

    @Autowired
    @Qualifier("autorMapper")
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<List<AutorDTO>> findAll() throws Exception {
        List<AutorDTO> list = service.getAll()
                .parallelStream()
                .map(autor -> mapper.map(autor, AutorDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable("id") int id) throws Exception {
        AutorDTO obj = mapper.map(service.findById(id), AutorDTO.class);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUNT  " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AutorDTO> save(@Valid @RequestBody AutorDTO autorDto) throws Exception {
        Autor obj = service.save(mapper.map(autorDto, Autor.class));
        return new ResponseEntity<>(mapper.map(obj, AutorDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AutorDTO> update(@Valid @RequestBody AutorDTO autorDto) throws Exception {
        Autor obj = service.save(mapper.map(autorDto, Autor.class));
        return new ResponseEntity<>(mapper.map(obj, AutorDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws Exception {
        Autor obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
