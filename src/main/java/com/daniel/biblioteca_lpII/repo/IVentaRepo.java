package com.daniel.biblioteca_lpII.repo;

import com.daniel.biblioteca_lpII.model.Venta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVentaRepo extends IGenericRepo<Venta,Integer>{

    List<Venta> findVentaByClienteIdCliente(Integer idCliente);
	
}	
