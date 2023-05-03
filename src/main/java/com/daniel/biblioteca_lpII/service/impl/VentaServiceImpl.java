package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Venta;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.repo.IVentaRepo;
import com.daniel.biblioteca_lpII.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaServiceImpl extends CRUDImpl<Venta,Integer> implements IVentaService {

	@Autowired
	private IVentaRepo repo;
	
	@Override
	IGenericRepo<Venta, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
