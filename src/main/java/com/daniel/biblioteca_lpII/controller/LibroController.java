package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.LibroDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.service.ILibroService;
import com.daniel.biblioteca_lpII.service.impl.LibroServiceImpl;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    @Autowired
    private ILibroService service;

    @Autowired
    @Qualifier("tipoMapper")
    private ModelMapper mapper;

    @GetMapping("/mostrar")
    private ResponseEntity<List<LibroDTO>> findAll() throws Exception {
        List<LibroDTO> list = service.getAll()
                .parallelStream()
                .map(lib -> mapper.map(lib, LibroDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //@GetMapping("/buscar/{id}")
    @GetMapping("/buscar")
    public ResponseEntity<LibroDTO> findById(@RequestParam("id") int id) throws Exception {
        LibroDTO obj = mapper.map(service.findById(id), LibroDTO.class);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUNT  " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/tipoV2")
    public ResponseEntity<List<Libro>> findByTipoV2(@RequestParam("tipo") String tipo){
        /*
        List<LibroDTO> list = service.findByTipoV2(tipo)
                .stream()
                .map(l -> mapper.map(l,LibroDTO.class))
                .collect(Collectors.toList());
         */
        try {
            Predicate<Libro> predicate = l -> l.getTipo().getTipo().contains(tipo);
            List<Libro> list = service.getAll()
                    .stream()
                    .filter(predicate)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tipo") //http://localhost:8080/api/libros/tipo?nombre=${tipo}
    public ResponseEntity<List<LibroDTO>> findByTipo(@RequestParam("nombre") String nombre) throws Exception{
        List<LibroDTO> list = service.findByNombreTipo(nombre)
                .parallelStream()
                .map(l -> mapper.map(l,LibroDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/titulo") //http://localhost:8080/api/libros/titulo?titulo=${titulo}
    public ResponseEntity<List<LibroDTO>> getByTituloLike(@RequestParam("titulo") String titulo) throws Exception{
        List<LibroDTO> list = service.getByTituloLike(titulo)
                .stream()
                .map(l -> mapper.map(l,LibroDTO.class))
                .collect(Collectors.toList());
        //EN EL CASO QUE NO HAYA COINCIDENCIA NO ME VA A MOSTRAR EL LIBRO BUSCADO PERO VA A LANZAR UN 404
        if(list.size()==0){
            throw new ModelNotFoundException("No se han encontrado coincidencias!!");
            /*
            return new ResponseEntity<>(service.getAll().stream()
                    .map(l -> mapper.map(l,LibroDTO.class))
                    .collect(Collectors.toList())
                    ,HttpStatus.NOT_FOUND);
             */
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/autor")
    public ResponseEntity<List<LibroDTO>> findByAutor(@RequestParam("autor") String autor) throws Exception{
        List<LibroDTO> list = service.findByAutor(autor)
                .stream()
                .map(l -> mapper.map(l, LibroDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    /*
    @GetMapping("/tituloV2")
    public ResponseEntity<List<LibroDTO>> findLibroWithStart(@RequestParam("titulo") String titulo) throws Exception{
        List<LibroDTO> list = service.findLibroWithStart(titulo)
                .stream()
                .map(l -> mapper.map(l,LibroDTO.class))
                .collect(Collectors.toList());
        if(list.size()==0){
            throw new ModelNotFoundException("No se han encontrado coincidencias!!");
        }
        return new ResponseEntity<>(list,HttpStatus.OK);

    }

     */


    @PostMapping
    public ResponseEntity<LibroDTO> save(@Valid @RequestBody LibroDTO clientDto) throws Exception {
        Libro obj = service.save(mapper.map(clientDto, Libro.class));
        return new ResponseEntity<>(mapper.map(obj, LibroDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LibroDTO> update(@Valid @RequestBody LibroDTO clientDto) throws Exception {
        Libro obj = service.save(mapper.map(clientDto, Libro.class));
        return new ResponseEntity<>(mapper.map(obj, LibroDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws Exception {
        Libro obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
