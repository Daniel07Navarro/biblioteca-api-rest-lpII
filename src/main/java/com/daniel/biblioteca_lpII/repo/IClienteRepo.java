package com.daniel.biblioteca_lpII.repo;

import org.springframework.stereotype.Repository;

import com.daniel.biblioteca_lpII.model.Cliente;

@Repository
public interface IClienteRepo extends IGenericRepo<Cliente,Integer>{

    Cliente findOneByNombreCompleto(String nombre);

    Cliente findOneByEmail(String email);
	
}	
