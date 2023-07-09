package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Cliente;
import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.model.Venta;
import com.daniel.biblioteca_lpII.model.VentaDetalle;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.repo.IVentaRepo;
import com.daniel.biblioteca_lpII.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VentaServiceImpl extends CRUDImpl<Venta,Integer> implements IVentaService {

	@Autowired
	private IVentaRepo repo;

	@Override
	IGenericRepo<Venta, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	//TRAER LOS LIBROS MAS VENDIDOS
	@Override
	public List<Libro> getMostSellerProduct() {
		Stream<List<VentaDetalle>> streamListDetalle = repo.findAll()
				.stream()
				.map(Venta::getVentaDetalles);
		Stream<VentaDetalle> streamDetalle = streamListDetalle.flatMap(Collection::stream);

		Map<Libro,Double>  byProduct = streamDetalle
				.collect(Collectors.groupingBy(VentaDetalle::getLibro,Collectors.summingDouble(VentaDetalle::getCantidad)));

		Set<Map.Entry<Libro,Double>> entry = byProduct.entrySet();
		List<Libro> list = byProduct.keySet().stream().toList();
		return list.stream().sorted(Comparator.comparingInt(Libro::getIdLibro).reversed()).collect(Collectors.toList());

		/*
		return repo.findAll()
				.stream()
				.flatMap(v -> v.getVentaDetalles().stream())
				.collect(Collectors.groupingBy(d -> d.getLibro(),Collectors.summingDouble(d -> d.getCantidad())))
				.entrySet().stream()
				.sorted(Map.Entry.<Libro,Double>comparingByValue().reversed())
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		 */
	}

	@Override
	public Optional<Venta> ventaMasAntigua() {
		Stream<List<VentaDetalle>> streamListDetalle = repo.findAll()
				.stream()
				.map(Venta::getVentaDetalles);
		Stream<VentaDetalle> streamDetalle = streamListDetalle.flatMap(Collection::stream);

		return repo.findAll().stream()
				.min(Comparator.comparing(v -> v.getVentaDetalles().stream()
						.map(VentaDetalle::getVenta)
						.map(Venta::getFechaVenta)
						.min(Comparator.naturalOrder())
						.orElse(null)));


		//return streamDetalle.collect(Collectors.toSet());
		//return streamDetalle.collect(Collectors.toList());
		/*
		return repo.findAll()
				.stream()
				.sorted(Comparator.comparing(Venta::getFechaVenta))
				//.limit(1)
				.collect(Collectors.toList());
		//return null;
		*/
	}

	@Override
	public Map<String, Double> clienteConMasCompras() {
		Stream<List<VentaDetalle>> streamLv = repo.findAll()
				.stream()
				.map(Venta::getVentaDetalles);
		Stream<VentaDetalle> streamVd = streamLv.flatMap(Collection::stream);
		Map<String,Double> map = streamVd
				.collect(Collectors.groupingBy(v -> v.getVenta().getCliente().getNombreCompleto()+ " "+ v.getVenta().getCliente().getApellido(),Collectors.summingDouble(VentaDetalle::getCantidad)));
		return map;
	}

	@Override
	public List<Venta> findByIdCliente(Integer id) throws Exception {
		return repo.findVentaByClienteIdCliente(id);
	}
}



