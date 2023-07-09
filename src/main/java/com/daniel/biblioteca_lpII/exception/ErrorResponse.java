package com.daniel.biblioteca_lpII.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Integer statusCode;

    private LocalDateTime dateTime;

    private String message;

    private String path;

}
