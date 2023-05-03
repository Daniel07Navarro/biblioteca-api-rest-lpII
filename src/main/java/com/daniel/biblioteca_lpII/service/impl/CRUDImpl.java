package com.daniel.biblioteca_lpII.service.impl;

import java.util.List;

import com.daniel.biblioteca_lpII.exception.ModelNotFoundException;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.service.ICRUD;

public abstract class CRUDImpl<T,ID> implements ICRUD<T, ID>{
	
	abstract IGenericRepo<T, ID> getRepo();

	@Override
	public List<T> getAll() throws Exception {
		// TODO Auto-generated method stub
		return getRepo().findAll();
	}

	@Override
	public T save(T t) throws Exception {
		// TODO Auto-generated method stub
		return getRepo().save(t);
	}

	@Override
	public T update(T t) throws Exception {
		// TODO Auto-generated method stub
		return getRepo().save(t);
	}

	@Override
	public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NOT FOUND "+id));
    }

	@Override
	public void deleteById(ID id) throws Exception {
		getRepo().deleteById(id);
		
	}

}
