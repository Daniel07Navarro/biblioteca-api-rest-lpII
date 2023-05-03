package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.LibroV2;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.repo.ILibroV2Repo;
import com.daniel.biblioteca_lpII.service.ILibroV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroV2ServiceImpl extends CRUDImpl<LibroV2,Integer> implements ILibroV2Service {

	@Autowired
	private ILibroV2Repo repo;
	
	@Override
	IGenericRepo<LibroV2, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
