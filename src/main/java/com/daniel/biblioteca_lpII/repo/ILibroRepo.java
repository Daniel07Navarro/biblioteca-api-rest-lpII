package com.daniel.biblioteca_lpII.repo;

import com.daniel.biblioteca_lpII.model.Libro;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepo extends IGenericRepo<Libro,Integer>{

    List<Libro> findByTipo_Tipo(String tipo);

    //USANDO JPQL
    @Query("FROM Libro l where l.titulo LIKE :titulo%")
    List<Libro> getByTituloLike(String titulo);

    //PROPIAS
    List<Libro> findByTipoLike(String name);

    List<Libro> findByAutor(String autor);
	
}	
