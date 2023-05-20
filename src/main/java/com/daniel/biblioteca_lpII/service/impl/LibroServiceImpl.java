package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.repo.ILibroRepo;
import com.daniel.biblioteca_lpII.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl extends CRUDImpl<Libro,Integer> implements ILibroService {

	@Autowired
	private ILibroRepo repo;

	@Override
	IGenericRepo<Libro, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public List<Libro> findByNombreTipo(String tipo) throws Exception {
		return repo.findByTipo_Tipo(tipo);
	}

	@Override
	public List<Libro> findByTipoV2(String tipo) throws Exception {
		Predicate<Libro> predicate = l -> l.getTipo().getTipo().contains(tipo);
		this.repo.findAll().forEach(System.out::println);
		return repo.findAll()
				.stream()
				.filter(predicate)
				.collect(Collectors.toList());
		//return list2;
	}
}
