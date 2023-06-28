package com.daniel.biblioteca_lpII.service;

import com.daniel.biblioteca_lpII.model.Cliente;

import java.util.List;
import java.util.Map;

public interface IClienteService extends ICRUD<Cliente,Integer>{

	//METODOS DISTINTOS A LOS DEL CRUD
    Cliente findOneByName(String name) throws Exception;

    Cliente findOneByEmail(String email) throws Exception;

    //List<Object> misDetalles(Integer id);
    List<Map<String,Object>> misDetalles(Integer id) throws Exception;
}
