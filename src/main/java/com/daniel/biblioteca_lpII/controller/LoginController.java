package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.ClienteDTO;
import com.daniel.biblioteca_lpII.exception.ValidationException;
import com.daniel.biblioteca_lpII.model.Cliente;
import com.daniel.biblioteca_lpII.security.*;

import com.daniel.biblioteca_lpII.service.IClienteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final JwtUserDetailsService userDetailsService;

    @Autowired
    private IClienteService service;

    @Autowired
    @Qualifier("clienteMapper")
    private ModelMapper mapper;


    @Autowired
    private SessionRegistry sessionRegistry;

    @PostMapping()
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req) throws Exception {
        authenticate(req.getUsername(), req.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<>(new JwtResponse(token),HttpStatus.OK);
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

    @GetMapping("/clienteLogeado")
    public ResponseEntity<ClienteDTO> traerUsuarioLogeado(Principal principal) throws Exception{
        String clienteNombre = principal.getName();
        Cliente cliente = service.findOneByName(clienteNombre);
        return new ResponseEntity<>(mapper.map(cliente,ClienteDTO.class), HttpStatus.OK);
    }

    @GetMapping("/timeValidation/token")
    public ResponseEntity<Map<String,Date>> getTimeToken(HttpServletRequest request){
        //HttpHeaders headers = new HttpHeaders();
        //HttpServletRequest headers;
        String token = jwtTokenUtil.getToken(request);
        if(token==null){
            throw new ValidationException("ERROR, ALGO ESTA MAL");
        }
        Date fecha = jwtTokenUtil.getExpirationDateFromToken(token);
        Map<String,Date> mapa = new HashMap<>();
        mapa.put("Hora expiracion",fecha);
        return new ResponseEntity<>(mapa,HttpStatus.OK);
    }

    @GetMapping("/tokenExpired")
    public ResponseEntity<Map<String,Boolean>> getValidationToken(HttpServletRequest request){
        String token = jwtTokenUtil.getToken(request);
        boolean tokenExpired = jwtTokenUtil.isTokenExpired(token);
        Map<String,Boolean> mapa = new HashMap<>();
        if(tokenExpired){
            mapa.put("Token expirado: ",true);
            return new ResponseEntity<>(mapa,HttpStatus.OK);
        }else{
            mapa.put("Token expirado: ",false);
        }
        return new ResponseEntity<>(mapa,HttpStatus.OK);
    }


    @GetMapping("/session")
    public ResponseEntity<Map<String,Object>> getSession(){
        String sesssionId = "";
        User userObj = null;
        List<Object> sesiones = sessionRegistry.getAllPrincipals();
        for(Object sesion:sesiones){
            if(sesion instanceof User){
                userObj= (User)sesion;
            }
            List<SessionInformation> informacionSesion = sessionRegistry.getAllSessions(sesion,false);
            for(SessionInformation sessionInformation:informacionSesion){
                sesssionId = sessionInformation.getSessionId();
            }
        }
        Map<String,Object> mapa = new HashMap<>();
        mapa.put("Usuario",userObj);
        mapa.put("Session id",sesssionId);

        return new ResponseEntity<>(mapa,HttpStatus.OK);
    }




}
