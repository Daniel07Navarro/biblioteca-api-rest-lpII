package com.daniel.biblioteca_lpII.controller;

import com.daniel.biblioteca_lpII.service.ILibroService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ILibroService service;

    @MockBean(name = "libroMapper")
    private ModelMapper mapper;

    @Test
    public void findAllTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/libros/mostrar")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
