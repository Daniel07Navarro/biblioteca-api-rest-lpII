package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.model.LibroV2;
import com.daniel.biblioteca_lpII.service.ILibroV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/librosV2")
public class LibroV2Controller {

    @Autowired
    private ILibroV2Service service;

    @GetMapping
    public ResponseEntity<List<LibroV2>> findAll() throws Exception{
        List<LibroV2> libros = service.getAll();
        for (LibroV2 libro : libros) {
            libro.cargarImagen();
        }
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

}
