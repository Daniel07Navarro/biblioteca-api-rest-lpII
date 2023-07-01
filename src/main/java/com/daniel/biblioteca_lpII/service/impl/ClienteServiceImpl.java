package com.daniel.biblioteca_lpII.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.biblioteca_lpII.model.Cliente;
import com.daniel.biblioteca_lpII.repo.IClienteRepo;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.service.IClienteService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente,Integer> implements IClienteService {

	@Autowired
	private IClienteRepo repo;
	
	@Override
	IGenericRepo<Cliente, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public Cliente findOneByName(String name) throws Exception {
		return repo.findOneByNombreCompleto(name);
	}

	@Override
	public Cliente findOneByEmail(String email) throws Exception {
		return repo.findOneByEmail(email);
	}

	@Override
	public List<Map<String,Object>> misDetalles(Integer id) {
		return repo.misDetalles(id);
	}

	@Override
	public List<Map<String,Object>> misDetallesV2(Integer id) throws Exception {
		List<Object[]> lista = repo.misDetallesV2(id); //TRAEMOS DEL REPO
		List<Map<String,Object>> listaDetalles = new ArrayList<>();

		Map<Integer,Map<String,Object>> mapaVentas = new HashMap<>();

		for (Object[] fila : lista) {
			Integer idVenta = (Integer) fila[0];
			String titulo = (String) fila[4];
			String autor = (String) fila[5];
			String fechaVenta = (String) fila[6];
			BigDecimal precio = (BigDecimal) fila[7];
			Short cantidad = (Short) fila[3];
			String imagen = (String) fila[8];
			Integer idVentaDetalle = (Integer) fila[9];

			if (!mapaVentas.containsKey(idVenta)) {
				Map<String, Object> ventaMapa = new HashMap<>();
				ventaMapa.put("idVenta", idVenta);
				ventaMapa.put("total", fila[1]);
				ventaMapa.put("impuesto", fila[2]);
				ventaMapa.put("ventaDetalles", new ArrayList<>());
				mapaVentas.put(idVenta, ventaMapa);
				listaDetalles.add(ventaMapa);
			}

			Map<String, Object> ventaMapa = mapaVentas.get(idVenta);
			List<Map<String, Object>> detalles = (List<Map<String, Object>>)ventaMapa.get("ventaDetalles");

			Map<String, Object> detalleMapa = new HashMap<>();
			detalleMapa.put("Cantidad", cantidad);
			detalleMapa.put("Titulo", titulo);
			detalleMapa.put("Autor", autor);
			detalleMapa.put("Fecha_Compra", fechaVenta);
			detalleMapa.put("Precio", precio);
			detalleMapa.put("Imagen", imagen);
			detalleMapa.put("idVentaDetalle", idVentaDetalle);

			detalles.add(detalleMapa);
		}

		return listaDetalles;
	}
}
