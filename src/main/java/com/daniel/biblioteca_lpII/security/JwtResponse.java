package com.daniel.biblioteca_lpII.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//CLASE S4

@Getter
@AllArgsConstructor
@ToString
public class JwtResponse {

    private final String token;

}