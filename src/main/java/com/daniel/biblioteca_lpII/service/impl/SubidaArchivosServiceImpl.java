package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.exception.ValidationException;
import com.daniel.biblioteca_lpII.service.ISubidaArchivosService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SubidaArchivosServiceImpl implements ISubidaArchivosService {

    @Value("${ruta}")
    private String rutaBase;

    private final long maxFileSize = 9 * 1024 * 1024; //9MB

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public Map<String, String> handleFileUpload(MultipartFile file) throws Exception {

        String filename = UUID.randomUUID().toString(); //Sirve para crear un nombre unico para que no se sobreescriban y se reemplazen

        String fileOriginalName = file.getOriginalFilename();

        byte[] bytes = file.getBytes();

        //VALIDAMOS EL TAMAÑO
        long fileSize = file.getSize();


        if (fileSize > maxFileSize) {
            return Map.of("Mensaje", "El archivo sobrepasa el tamaño permitido");
        }

        //Manejaremos el tipo de archivo
        if (!fileOriginalName.endsWith(".jpg") && !fileOriginalName.endsWith(".jpeg") && !fileOriginalName.endsWith(".png") && !fileOriginalName.endsWith(".pdf")) {
            //return "Solo archivos jps,png, jpeg y pdf"; //o puede lanzar un bad request
            return Map.of("Mensaje", "Solo archivos jps,png, jpeg y pdf");
        }
        //Debemos capturar la extension del archivo
        String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
        String newFileName = filename.concat(fileExtension);

        //creamos la carpeta donde se va a alojar
        validarExtension(fileExtension, bytes, newFileName);

        return Map.of("Mensaje", "Archivo subido");
    }

    public void validarExtension(String fileExtension, byte[] bytes, String newFileName) throws Exception {
        File folder;
        if (fileExtension.equals(".jpg") || fileExtension.equals(".jpeg") || fileExtension.equals(".png")) {
            folder = new File(rutaBase.concat("imagenes"));
            if (!folder.exists()) {
                folder.mkdirs(); //para crear la carpeta
            }
            Path ruta = Paths.get("src/main/resources/imagenes/" + newFileName);

            Files.write(ruta, bytes);
        }
        if (fileExtension.equals(".pdf")) {
            folder = new File(rutaBase.concat("pdf"));
            if (!folder.exists()) {
                folder.mkdirs(); //para crear la carpeta
            }
            Path ruta = Paths.get("src/main/resources/pdf/" + newFileName);

            Files.write(ruta, bytes);
        }
    }
}
