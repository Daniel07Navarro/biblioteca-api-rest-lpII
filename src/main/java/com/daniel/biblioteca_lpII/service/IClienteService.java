package com.daniel.biblioteca_lpII.service;

import com.daniel.biblioteca_lpII.model.Cliente;

public interface IClienteService extends ICRUD<Cliente,Integer>{

	//METODOS DISTINTOS A LOS DEL CRUD
    Cliente findOneByName(String name) throws Exception;

}
