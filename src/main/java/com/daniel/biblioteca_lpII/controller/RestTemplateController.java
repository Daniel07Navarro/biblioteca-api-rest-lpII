package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.dto.ClienteDTO;
import com.daniel.biblioteca_lpII.model.Cliente;
import com.daniel.biblioteca_lpII.security.JwtRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestTemplateController {

    @Autowired
    private final RestTemplate restTemplate;

    @GetMapping("/test1")
    public ResponseEntity<?> test1() {
        String url = "https://api.escuelajs.co/api/v1/products"; //CONSUMIENDO ESA URL
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<?>> typeRefer = new ParameterizedTypeReference<List<?>>() {
        };
        return restTemplate.exchange(url, HttpMethod.GET, entity, typeRefer);
    }

    @GetMapping("/test2")
    public ResponseEntity<List<?>> test2(@RequestParam("id") Integer id) {
        String url = "https://api.escuelajs.co/api/v1/products?categoryId={id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<?>> typeRefer = new ParameterizedTypeReference<List<?>>() {
        };
        return restTemplate.exchange(url, HttpMethod.GET, entity, typeRefer);
    }

    @GetMapping("/test3")
    public ResponseEntity<?> test3(@RequestParam("categoryId") Integer categoryId) {
        String url = "https://api.escuelajs.co/api/v1/products?categoryId={categoryId}";

        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("categoryId", categoryId);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);


        return restTemplate.exchange(url, HttpMethod.GET, entity, Map.class, uriVariables);
    }

    @PostMapping("/test4")
    public ResponseEntity<ClienteDTO> test4(@RequestBody @Valid ClienteDTO clienteDTO) {
        String url = "http://localhost:8080/api/clientes/registrar";
        HttpEntity<ClienteDTO> request = new HttpEntity<>(clienteDTO);

        ClienteDTO obj = restTemplate.postForObject(url, request, ClienteDTO.class);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //return restTemplate.exchange(url,HttpMethod.POST,request,ClienteDTO.class);
    }

    @GetMapping("/test8")
    public ResponseEntity<?> test8() throws Exception {
        //simulamos el inicio de session
        final String url_login = "http://localhost:8080/login";
        JwtRequest userRequest = new JwtRequest("andrea", "123");
        String userRequestJSON = new ObjectMapper().writeValueAsString(userRequest);

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        authHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> entityLogin = new HttpEntity<>(userRequestJSON, authHeaders);

        ResponseEntity<String> authenticationResponse = restTemplate.exchange(url_login, HttpMethod.POST, entityLogin, String.class);

        //Enviando el token para pedir los datos
        String token = "Bearer " + authenticationResponse.getBody().split(":")[1].replace("\"", "").replace("}", "");


        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", token);

        String urlGet = "http://localhost:8080/api/clientes";
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<?>> typeRefer = new ParameterizedTypeReference<List<?>>() {
        };

        return restTemplate.exchange(urlGet, HttpMethod.GET, entity, typeRefer);
    }

    @GetMapping("/test9")
    public ResponseEntity<?> test9() throws Exception {
        final String authURL = "https://api.escuelajs.co/api/v1/auth/login";

        Map<String, String> users = new HashMap<>();
        users.put("email", "john@mail.com");
        users.put("password", "changeme");
        String userRequestJSON = new ObjectMapper().writeValueAsString(users);

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        authHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entityLogin = new HttpEntity<>(userRequestJSON, authHeaders);

        ResponseEntity<String> authenticationResponse = restTemplate.exchange(authURL, HttpMethod.POST, entityLogin, String.class);

        String token = "Bearer " + authenticationResponse.getBody().split(":")[1].replace("\"", "").replace("}", "");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", token);

        String urlGet = "https://api.escuelajs.co/api/v1/auth/profile";
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<?>> typeRefer = new ParameterizedTypeReference<List<?>>() {
        };
        return restTemplate.exchange(urlGet, HttpMethod.GET, entity, typeRefer);
    }

}
