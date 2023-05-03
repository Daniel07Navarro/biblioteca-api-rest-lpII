package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.AreaDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Area;
import com.daniel.biblioteca_lpII.service.IAreaService;
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
@RequestMapping("/api/areas")
//@CrossOrigin(origins = "*")
public class AreaController {

    @Autowired
    private IAreaService service;

    @Autowired
    @Qualifier("areaMapper")
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<List<AreaDTO>> findAll() throws Exception {
        List<AreaDTO> list = service.getAll()
                .parallelStream()
                .map(a -> mapper.map(a, AreaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDTO> findById(@PathVariable("id") int id) throws Exception {
        AreaDTO obj = mapper.map(service.findById(id), AreaDTO.class);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUNT  " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AreaDTO> save(@Valid @RequestBody AreaDTO areaDto) throws Exception {
        Area obj = service.save(mapper.map(areaDto, Area.class));
        return new ResponseEntity<>(mapper.map(obj, AreaDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AreaDTO> update(@Valid @RequestBody AreaDTO areaDto) throws Exception {
        Area obj = service.save(mapper.map(areaDto, Area.class));
        return new ResponseEntity<>(mapper.map(obj, AreaDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws Exception {
        Area obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
