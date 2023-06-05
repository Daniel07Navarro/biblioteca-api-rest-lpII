package com.daniel.biblioteca_lpII.service;

import com.daniel.biblioteca_lpII.model.Libro;

import java.util.List;

public interface ILibroService extends ICRUD<Libro,Integer>{

	//METODOS DISTINTOS A LOS DEL CRUD
    List<Libro> findByNombreTipo(String tipo) throws Exception;

    List<Libro> findByTipoV2(String tipo) throws Exception;

    List<Libro> getByTituloLike(String titulo) throws Exception;

    //List<Libro> findLibroWithStart(String titutlo) throws Exception;

    List<Libro> getByAutor(String autor) throws Exception;

    List<Libro> findByTipo_TipoAndAutor(String tipo,String autor) throws Exception;

    List<Libro> findByEditorial(String editorial) throws Exception;

    List<Libro> findByTipo_TipoAndAutorAndEditorial(String tipo,String autor,String editorial) throws Exception;

    List<Libro> findByAutorAndEditorial(String autor,String editorial) throws Exception;

    List<Libro> findByTipo_TipoAndAndEditorial_Nombre(String tipo,String editorial) throws Exception;

    List<Libro> findByFiltros(String tipo,String autor,String editorial);

}
