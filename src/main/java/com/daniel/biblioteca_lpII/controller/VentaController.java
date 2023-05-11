package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.VentaDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.model.Venta;
import com.daniel.biblioteca_lpII.service.ILibroService;
import com.daniel.biblioteca_lpII.service.IVentaService;
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
@RequestMapping("/api/ventas")
//@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private IVentaService service;

    @Autowired
    private ILibroService libroService;

    @Autowired
    @Qualifier("ventaMapper")
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<List<VentaDTO>> findAll() throws Exception {
        List<VentaDTO> list = service.getAll()
                .parallelStream()
                .map(venta -> mapper.map(venta, VentaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> findById(@PathVariable("id") int id) throws Exception {
        VentaDTO obj = mapper.map(service.findById(id), VentaDTO.class);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUNT  " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VentaDTO> save(@Valid @RequestBody VentaDTO ventaDto) throws Exception {
        Venta obj = service.save(mapper.map(ventaDto, Venta.class));
        /*
        int posicion;
        for (int i =0;i<obj.getVentaDetalles().size();i++){
            if(obj){

            }
        }
        Libro libro = libroService.findById(obj.getVentaDetalles().get(0).getLibro().getIdLibro());
        */
        return new ResponseEntity<>(mapper.map(obj, VentaDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<VentaDTO> update(@Valid @RequestBody VentaDTO clientDto) throws Exception {
        Venta obj = service.save(mapper.map(clientDto, Venta.class));
        return new ResponseEntity<>(mapper.map(obj, VentaDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws Exception {
        Venta obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
