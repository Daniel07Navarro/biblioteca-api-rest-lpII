package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Autor;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.repo.IAutorRepo;
import com.daniel.biblioteca_lpII.service.IAutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServiceImpl extends CRUDImpl<Autor,Integer> implements IAutorService {

	@Autowired
	private IAutorRepo repo;
	
	@Override
	IGenericRepo<Autor, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
