package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Tipo;
import com.daniel.biblioteca_lpII.repo.ITipoRepo;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.service.ITipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoServiceImpl extends CRUDImpl<Tipo,Integer> implements ITipoService {

	@Autowired
	private ITipoRepo repo;
	
	@Override
	IGenericRepo<Tipo, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
