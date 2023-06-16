package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.VentaDetalle;
import com.daniel.biblioteca_lpII.repo.IVentaDetalleRepo;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.service.IVentaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaDetalleServiceImpl extends CRUDImpl<VentaDetalle,Integer> implements IVentaDetalleService {

	@Autowired
	private IVentaDetalleRepo repo;
	
	@Override
	IGenericRepo<VentaDetalle, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
