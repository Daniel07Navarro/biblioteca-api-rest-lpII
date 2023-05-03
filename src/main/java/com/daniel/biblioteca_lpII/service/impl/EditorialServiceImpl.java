package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Editorial;
import com.daniel.biblioteca_lpII.repo.IEditorialRepo;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.service.IEditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServiceImpl extends CRUDImpl<Editorial,Integer> implements IEditorialService {

	@Autowired
	private IEditorialRepo repo;
	
	@Override
	IGenericRepo<Editorial, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
