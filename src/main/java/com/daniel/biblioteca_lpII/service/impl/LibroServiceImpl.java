package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.repo.ILibroRepo;
import com.daniel.biblioteca_lpII.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl extends CRUDImpl<Libro,Integer> implements ILibroService {

	@Autowired
	private ILibroRepo repo;
	
	@Override
	IGenericRepo<Libro, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
