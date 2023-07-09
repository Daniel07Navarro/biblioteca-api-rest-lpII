package com.daniel.biblioteca_lpII;

import com.daniel.biblioteca_lpII.controller.LibroController;
import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.service.ILibroService;
import com.daniel.biblioteca_lpII.service.impl.LibroServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BibliotecaLpIiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaLpIiApplication.class, args);
    }

}
