package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.EditorialDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Editorial;
import com.daniel.biblioteca_lpII.service.IEditorialService;
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
@RequestMapping("/api/editoriales")
//@CrossOrigin(origins = "*")
public class EditorialController {

    @Autowired
    private IEditorialService service;

    @Autowired
    @Qualifier("autorMapper")
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<List<EditorialDTO>> findAll() throws Exception {
        List<EditorialDTO> list = service.getAll()
                .parallelStream()
                .map(autor -> mapper.map(autor, EditorialDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditorialDTO> findById(@PathVariable("id") int id) throws Exception {
        EditorialDTO obj = mapper.map(service.findById(id), EditorialDTO.class);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUNT  " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EditorialDTO> save(@Valid @RequestBody EditorialDTO clientDto) throws Exception {
        Editorial obj = service.save(mapper.map(clientDto, Editorial.class));
        return new ResponseEntity<>(mapper.map(obj, EditorialDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EditorialDTO> update(@Valid @RequestBody EditorialDTO clientDto) throws Exception {
        Editorial obj = service.save(mapper.map(clientDto, Editorial.class));
        return new ResponseEntity<>(mapper.map(obj, EditorialDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws Exception {
        Editorial obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
