package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.VentaDTO;
import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.model.Cliente;
import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.model.Venta;
import com.daniel.biblioteca_lpII.repo.IClienteRepo;
import com.daniel.biblioteca_lpII.security.JwtTokenUtil;
import com.daniel.biblioteca_lpII.service.IEmailService;
import com.daniel.biblioteca_lpII.service.ILibroService;
import com.daniel.biblioteca_lpII.service.IVentaService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService service;

    @Autowired
    private ILibroService libroService;

    @Autowired
    @Qualifier("ventaMapper")
    private ModelMapper mapper;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IClienteRepo clienteRepo;

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

    @GetMapping("/detalles")
    public ResponseEntity<List<VentaDTO>> findByIdClient(@RequestParam("id") Integer id) throws Exception{
        List<VentaDTO> lista = service.findByIdCliente(id)
                .stream()
                .map( l-> mapper.map(l,VentaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @GetMapping("/masVendido")
    public ResponseEntity<?> libroMasVendido() throws Exception{
        //Map<String,Double> map = service.getMostSellerProduct();
        return new ResponseEntity<>(service.getMostSellerProduct(),HttpStatus.OK);
    }

    @GetMapping("/masAntigua")
    public ResponseEntity<?> ventaMasAntigua() throws Exception{
        return new ResponseEntity<>(service.ventaMasAntigua(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VentaDTO> save(@Valid @RequestBody VentaDTO ventaDto, HttpServletRequest request) throws Exception {
        Venta obj = service.save(mapper.map(ventaDto, Venta.class));
        String token = jwtTokenUtil.getToken(request);
        String clienteNombre = jwtTokenUtil.getUsernameFromToken(token);
        Cliente cliente = clienteRepo.findOneByNombreCompleto(clienteNombre);
        emailService.sendEmail(cliente.getEmail(),"Realizaste una compra","Venta confirmada");
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
