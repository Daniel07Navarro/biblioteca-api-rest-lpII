package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Area;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.repo.IAreaRepo;
import com.daniel.biblioteca_lpII.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl extends CRUDImpl<Area,Integer> implements IAreaService {

	@Autowired
	private IAreaRepo repo;
	
	@Override
	IGenericRepo<Area, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
