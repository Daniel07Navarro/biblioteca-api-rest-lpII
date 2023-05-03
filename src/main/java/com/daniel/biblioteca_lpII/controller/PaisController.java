package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.PaisDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Pais;
import com.daniel.biblioteca_lpII.service.IPaisService;
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
@RequestMapping("/api/paises")
//@CrossOrigin(origins = "*")
public class PaisController {

    @Autowired
    private IPaisService service;

    @Autowired
    @Qualifier("paisMapper")
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<List<PaisDTO>> findAll() throws Exception {
        List<PaisDTO> list = service.getAll()
                .parallelStream()
                .map(pais -> mapper.map(pais, PaisDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> findById(@PathVariable("id") int id) throws Exception {
        PaisDTO obj = mapper.map(service.findById(id), PaisDTO.class);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUNT  " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaisDTO> save(@Valid @RequestBody PaisDTO clientDto) throws Exception {
        Pais obj = service.save(mapper.map(clientDto, Pais.class));
        return new ResponseEntity<>(mapper.map(obj, PaisDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PaisDTO> update(@Valid @RequestBody PaisDTO clientDto) throws Exception {
        Pais obj = service.save(mapper.map(clientDto, Pais.class));
        return new ResponseEntity<>(mapper.map(obj, PaisDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws Exception {
        Pais obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
