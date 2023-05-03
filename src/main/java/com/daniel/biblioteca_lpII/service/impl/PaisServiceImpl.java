package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Pais;
import com.daniel.biblioteca_lpII.repo.IPaisRepo;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.service.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisServiceImpl extends CRUDImpl<Pais,Integer> implements IPaisService {

	@Autowired
	private IPaisRepo repo;
	
	@Override
	IGenericRepo<Pais, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
