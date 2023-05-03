package com.daniel.biblioteca_lpII.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LibroV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idLibro2;

    private String titulo;

    private String imagen;

    private byte[] imagenBytes;

    public void cargarImagen(){
        Path rutaImagen = Paths.get(this.imagen);
        try {
            this.imagenBytes = Files.readAllBytes(rutaImagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
