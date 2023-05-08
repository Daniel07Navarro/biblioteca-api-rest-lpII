package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.ClienteDTO;
import com.daniel.biblioteca_lpII.security.JwtRequest;
import com.daniel.biblioteca_lpII.security.JwtResponse;
import com.daniel.biblioteca_lpII.security.JwtTokenUtil;
import com.daniel.biblioteca_lpII.security.JwtUserDetailsService;
import com.daniel.biblioteca_lpII.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @Autowired
    private IClienteService service;

    @Autowired
    @Qualifier("clienteMapper")
    private ModelMapper mapper;


    @PostMapping()
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req) throws Exception {
        authenticate(req.getUsername(), req.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping("/usuarioLogeado")
    public ResponseEntity<ClienteDTO> traerUsuarioLogeado(Principal principal) throws Exception{
        String clienteNombre = principal.getName();
        return new ResponseEntity<>(mapper.map(service.findOneByName(clienteNombre),ClienteDTO.class), HttpStatus.OK);
    }


}
