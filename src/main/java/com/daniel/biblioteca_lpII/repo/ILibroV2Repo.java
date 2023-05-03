package com.daniel.biblioteca_lpII.repo;

import com.daniel.biblioteca_lpII.model.LibroV2;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ILibroV2Repo extends IGenericRepo<LibroV2,Integer>{
}
