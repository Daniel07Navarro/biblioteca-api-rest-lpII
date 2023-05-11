package com.daniel.biblioteca_lpII.repo;

import com.daniel.biblioteca_lpII.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepo extends IGenericRepo<Libro,Integer>{

    List<Libro> findByTipo_Tipo(String tipo);
	
}	
