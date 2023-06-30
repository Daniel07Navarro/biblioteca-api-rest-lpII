package com.daniel.biblioteca_lpII.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ISubidaArchivosService {

    Map<String,String> handleFileUpload(MultipartFile file) throws Exception;

}
