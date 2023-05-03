package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.TipoDTO;
import com.daniel.biblioteca_lpII.dto.TipoDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Tipo;
import com.daniel.biblioteca_lpII.model.Tipo;
import com.daniel.biblioteca_lpII.service.ITipoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipos")
//@CrossOrigin(origins = "*")
public class TipoController {

    @Autowired
    private ITipoService service;

    @Autowired
    @Qualifier("tipoMapper")
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<List<TipoDTO>> findAll() throws Exception {
        List<TipoDTO> list = service.getAll()
                .parallelStream()
                .map(tipo -> mapper.map(tipo, TipoDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDTO> findById(@PathVariable("id") int id) throws Exception {
        TipoDTO obj = mapper.map(service.findById(id), TipoDTO.class);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUNT  " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TipoDTO> save(@Valid @RequestBody TipoDTO clientDto) throws Exception {
        Tipo obj = service.save(mapper.map(clientDto, Tipo.class));
        return new ResponseEntity<>(mapper.map(obj, TipoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TipoDTO> update(@Valid @RequestBody TipoDTO clientDto) throws Exception {
        Tipo obj = service.save(mapper.map(clientDto, Tipo.class));
        return new ResponseEntity<>(mapper.map(obj, TipoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws Exception {
        Tipo obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
