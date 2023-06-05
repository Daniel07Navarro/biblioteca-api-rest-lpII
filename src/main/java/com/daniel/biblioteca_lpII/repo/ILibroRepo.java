package com.daniel.biblioteca_lpII.repo;

import com.daniel.biblioteca_lpII.model.Libro;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepo extends IGenericRepo<Libro,Integer>, JpaSpecificationExecutor<Libro> {

    List<Libro> findByTipo_Tipo(String tipo);


    List<Libro> findByEditorial_Nombre(String nombreEditorial);

    //USANDO JPQL
    @Query("FROM Libro l where l.titulo LIKE :titulo%")
    List<Libro> getByTituloLike(String titulo);

    //PROPIAS
    List<Libro> findByTipoLike(String name);

    @Query("FROM Libro l where l.autor LIKE :autor%")
    List<Libro> getByAutor(String autor);

    List<Libro> findByTipo_TipoAndAutor(String tipo,String autor);

    List<Libro> findByTipo_TipoAndAutorAndEditorial_Nombre(String tipo,String autor,String editorial);

    List<Libro> findByAutorAndEditorial_Nombre(String autor,String editorial);

    List<Libro> findByTipo_TipoAndAndEditorial_Nombre(String tipo,String editorial);


	
}	
