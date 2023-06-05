package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.model.Editorial;
import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.model.Tipo;
import com.daniel.biblioteca_lpII.repo.IGenericRepo;
import com.daniel.biblioteca_lpII.repo.ILibroRepo;
import com.daniel.biblioteca_lpII.service.ILibroService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl extends CRUDImpl<Libro, Integer> implements ILibroService {

    @Autowired
    private ILibroRepo repo;

    @PersistenceContext
    private EntityManager entityManager;

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
        java.util.function.Predicate<Libro> predicate = l -> l.getTipo().getTipo().contains(tipo);
        this.repo.findAll().forEach(System.out::println);
        return repo.findAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
        //return list2;
    }

    @Override
    public List<Libro> getByTituloLike(String titulo) throws Exception {
        return repo.getByTituloLike(titulo);
    }

    @Override
    public List<Libro> getByAutor(String autor) throws Exception {
        return repo.getByAutor(autor);
    }

    @Override
    public List<Libro> findByTipo_TipoAndAutor(String tipo, String autor) throws Exception {
        return repo.findByTipo_TipoAndAutor(tipo, autor);
    }

    @Override
    public List<Libro> findByEditorial(String editorial) throws Exception {
        return repo.findByEditorial_Nombre(editorial);
    }

    @Override
    public List<Libro> findByTipo_TipoAndAutorAndEditorial(String tipo, String autor, String editorial) throws Exception {
        return repo.findByTipo_TipoAndAutorAndEditorial_Nombre(tipo, autor, editorial);
    }


    @Override
    public List<Libro> findByAutorAndEditorial(String autor, String editorial) throws Exception {
        return repo.findByAutorAndEditorial_Nombre(autor, editorial);
    }

    @Override
    public List<Libro> findByTipo_TipoAndAndEditorial_Nombre(String tipo, String editorial) throws Exception {
        return repo.findByTipo_TipoAndAndEditorial_Nombre(tipo, editorial);
    }

    @Override
    public List<Libro> findByFiltros(String tipo, String autor, String editorial) {
        //UNA LAMBDA que lleva un objeto de tipo Specification
        List<Libro> list = repo.findAll((Root<Libro> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!tipo.equals("")) {
                //SE HACE UNA CONVERSION PORQUE ES UN OBJETO
                Join<Libro, Tipo> tipoJoin = root.join("tipo"); //en atributte name va el nombre de la tabla
                predicates.add(criteriaBuilder.equal(tipoJoin.get("tipo"), tipo));
            }

            if (!autor.equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("autor"), autor));
            }

            if (!editorial.equals("")) {
                //SE HACE UNA CONVERSION POR QUE ES UN OBJETO
                Join<Libro, Editorial> editorialJoin = root.join("editorial"); //en atributte name va el nombre de la tabla
                predicates.add(criteriaBuilder.equal(editorialJoin.get("nombre"), editorial)); //el .get("nombre") se refiere al campo con el nombre en la tabla editorial
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return list;
    }

}
