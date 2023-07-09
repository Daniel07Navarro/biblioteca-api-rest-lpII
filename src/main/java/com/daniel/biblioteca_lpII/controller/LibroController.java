package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.LibroDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.service.ILibroService;
import com.daniel.biblioteca_lpII.service.impl.LibroServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private ILibroService service;

    @Autowired
    @Qualifier("libroMapper")
    private ModelMapper mapper;

    @GetMapping("/mostrar")
    @Operation(summary = "LISTAR LIBROS",description = "Hace la llamada a la base de datos para mostrar todo los libros")
    public ResponseEntity<List<LibroDTO>> findAll() throws Exception {
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

    @GetMapping("/tipo") //http://localhost:8080/api/libros/tipo?nombre=${tipo}
    public ResponseEntity<List<LibroDTO>> findByTipo(@RequestParam("nombre") String nombre) throws Exception {
        List<LibroDTO> list = service.findByNombreTipo(nombre)
                .parallelStream()
                .map(l -> mapper.map(l, LibroDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/titulo") //http://localhost:8080/api/libros/titulo?titulo=${titulo}
    public ResponseEntity<List<LibroDTO>> getByTituloLike(@RequestParam("titulo") String titulo) throws Exception {
        List<LibroDTO> list = service.getByTituloLike(titulo)
                .stream()
                .map(l -> mapper.map(l, LibroDTO.class))
                .collect(Collectors.toList());
        //EN EL CASO QUE NO HAYA COINCIDENCIA NO ME VA A MOSTRAR EL LIBRO BUSCADO PERO VA A LANZAR UN 404
        if (list.size() == 0) {
            throw new ModelNotFoundException("No se han encontrado coincidencias!!");
            /*
            return new ResponseEntity<>(service.getAll().stream()
                    .map(l -> mapper.map(l,LibroDTO.class))
                    .collect(Collectors.toList())
                    ,HttpStatus.NOT_FOUND);
             */
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/autor")
    public ResponseEntity<List<LibroDTO>> findByAutor(@RequestParam("autor") String autor) throws Exception {
        List<LibroDTO> list = service.getByAutor(autor)
                .parallelStream()
                .map(l -> mapper.map(l, LibroDTO.class))
                .collect(Collectors.toList());
         /*
        USANDO PROGRAMACION FUNCIONAL
        List<LibroDTO> lista = service.getAll()
                .stream()
                .filter(l -> l.getAutor().startsWith(autor))
                .map(l -> mapper.map(l,LibroDTO.class))
                .collect(Collectors.toList());

        */
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/editorial")
    public ResponseEntity<List<LibroDTO>> findByEditorial(@RequestParam("editorial") String editorial) throws Exception {
        List<LibroDTO> list = service.findByEditorial(editorial)
                .stream()
                .map(l -> mapper.map(l, LibroDTO.class))
                .collect(Collectors.toList());
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<LibroDTO>> findByAutorAndEditorial(String autor, String editorial) throws Exception {
        List<LibroDTO> list = service.findByAutorAndEditorial(autor, editorial)
                .stream()
                .map(l -> mapper.map(l, LibroDTO.class))
                .collect(Collectors.toList());
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    public ResponseEntity<List<LibroDTO>> findByTipo_TipoAndAndEditorial_Nombre(String tipo, String editorial) throws Exception {
        List<LibroDTO> list = service.findByTipo_TipoAndAndEditorial_Nombre(tipo, editorial)
                .stream()
                .map(l -> mapper.map(l, LibroDTO.class))
                .collect(Collectors.toList());
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<LibroDTO>> findByFiltros(
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "autor", required = false) String autor,
            @RequestParam(value = "editorial", required = false) String editorial,
            @RequestParam(value = "titulo", required = false) String titulo) throws Exception {
        List<LibroDTO> list = service.findByFiltros(tipo, autor, editorial, titulo)
                .stream()
                .map(l -> mapper.map(l, LibroDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    //http://localhost:8080/api/libros/busqueda?tipo=&autor=&editorial=
    /*
    @GetMapping("/busqueda")
    public ResponseEntity<List<LibroDTO>> findByParameters(
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "autor", required = false) String autor,
            @RequestParam(value = "editorial", required = false) String editorial) throws Exception {
        List<LibroDTO> list;
        if (tipo.equals("") && autor.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (autor.equals("") && editorial.equals("") && !tipo.equals("")) {
            return findByTipo(tipo);
        } else if (editorial.equals("") && !autor.equals("") && !tipo.equals("")) {
            list = service.findByTipo_TipoAndAutor(tipo, autor)
                    .stream()
                    .map(l -> mapper.map(l, LibroDTO.class))
                    .collect(Collectors.toList());
        } else if (tipo.equals("") && !autor.equals("") && editorial.equals("")) {
            return findByAutor(autor);
        } else if (tipo.equals("") && !autor.equals("") && !editorial.equals("")) {
            //concidencia por autor y editorial
            return findByAutorAndEditorial(autor, editorial);
        } else if (tipo.equals("") && autor.equals("") && !editorial.equals("")) {
            //buscar por editorial
            return findByEditorial(editorial);
        } else if (!tipo.equals("") && autor.equals("") && !editorial.equals("")) {
            //tipo y editorial lleno
            return findByTipo_TipoAndAndEditorial_Nombre(tipo, editorial);
        } else {
            //LOS 3 LLENOS
            list = service.findByTipo_TipoAndAutorAndEditorial(tipo, autor, editorial)
                    .stream()
                    .map(l -> mapper.map(l, LibroDTO.class))
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
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


}
