package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.VentaDetalleDTO;
import com.daniel.biblioteca_lpII.service.IVentaDetalleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/detalles")
public class VentaDetalleController {

    @Autowired
    @Qualifier("ventaDetalleMapper")
    private ModelMapper mapper;


    @Autowired
    private IVentaDetalleService service;

    @GetMapping("/mostrar")
    public ResponseEntity<List<VentaDetalleDTO>> findAll() throws Exception{
        List<VentaDetalleDTO> list = service.getAll()
                .stream()
                .map(vd -> mapper.map(vd,VentaDetalleDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<VentaDetalleDTO> findById(@RequestParam("id") int id) throws Exception{
        VentaDetalleDTO obj = mapper.map(service.findById(id),VentaDetalleDTO.class);
        return new ResponseEntity<>(obj,HttpStatus.OK); 
    }

}
