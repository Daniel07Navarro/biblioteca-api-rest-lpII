package com.daniel.biblioteca_lpII.service;

import java.util.List;

public interface ICRUD<T,ID> {

	List<T> getAll() throws Exception;
	
	T save(T t) throws Exception;
	
	T update(T t) throws Exception;
	
	T findById(ID id) throws Exception;
	
	void deleteById(ID id) throws Exception;
}
