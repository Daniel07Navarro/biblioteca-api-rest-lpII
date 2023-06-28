package com.daniel.biblioteca_lpII.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.biblioteca_lpII.model.Cliente;
import com.daniel.biblioteca_lpII.repo.IClienteRepo;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.service.IClienteService;

import java.util.List;
import java.util.Map;

@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente,Integer> implements IClienteService {

	@Autowired
	private IClienteRepo repo;
	
	@Override
	IGenericRepo<Cliente, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public Cliente findOneByName(String name) throws Exception {
		return repo.findOneByNombreCompleto(name);
	}

	@Override
	public Cliente findOneByEmail(String email) throws Exception {
		return repo.findOneByEmail(email);
	}

	@Override
	public List<Map<String,Object>> misDetalles(Integer id) {
		return repo.misDetalles(id);
	}
}
