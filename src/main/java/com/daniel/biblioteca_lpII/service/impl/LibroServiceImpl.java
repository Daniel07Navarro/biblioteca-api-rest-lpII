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
    public List<Libro> findByFiltros(String tipo, String autor, String editorial, String titulo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Libro> consulta = cb.createQuery(Libro.class);
        Root<Libro> root = consulta.from(Libro.class);
        List<Predicate> predicates = new ArrayList<>();

        if (titulo != null && !titulo.equals("")) {
            predicates.add(cb.like(root.get("titulo"), titulo + "%")); //para que empieze con
        }
        if (tipo != null && !tipo.equals("")) {
            //SE HACE UNA CONVERSION PORQUE ES UN OBJETO
            Join<Libro, Tipo> tipoJoin = root.join("tipo"); //en atributte name va el nombre de la tabla
            predicates.add(cb.equal(tipoJoin.get("tipo"), tipo));
        }
        if (autor != null && !autor.equals("")) {
            predicates.add(cb.like(root.get("autor"), autor+"%"));
        }
        if (editorial != null && !editorial.equals("")) {
            //SE HACE UNA CONVERSION POR QUE ES UN OBJETO
            Join<Libro, Editorial> editorialJoin = root.join("editorial"); //en atributte name va el nombre de la tabla
            predicates.add(cb.equal(editorialJoin.get("nombre"), editorial)); //el .get("nombre") se refiere al campo con el nombre en la tabla editorial
        }
        consulta.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<Libro> typedQuery = entityManager.createQuery(consulta);
        return typedQuery.getResultList();
    }

    /*OTRA FORMA DE REALIZAR EL FILTRADO DINAMICO
    //USANDO UN OBJETO DE TIPO Specification
    //UNA LAMBDA que lleva un objeto de tipo Specification

        Specification<Libro> filtro = new Specification<Libro>() {
            @Override
            public Predicate toPredicate(Root<Libro> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicados = new ArrayList<>();
                if (tipo != null && !tipo.equals("")) {
                    //SE HACE UNA CONVERSION PORQUE ES UN OBJETO
                    Join<Libro, Tipo> tipoJoin = root.join("tipo"); //en atributte name va el nombre de la tabla
                    predicados.add(criteriaBuilder.equal(tipoJoin.get("tipo"), tipo));
                }
                if (autor != null && !autor.equals("")) {
                    predicados.add(criteriaBuilder.equal(root.get("autor"), autor));
                }
                if (editorial != null && !editorial.equals("")) {
                    //SE HACE UNA CONVERSION POR QUE ES UN OBJETO
                    Join<Libro, Editorial> editorialJoin = root.join("editorial"); //en atributte name va el nombre de la tabla
                    predicados.add(criteriaBuilder.equal(editorialJoin.get("nombre"), editorial)); //el .get("nombre") se refiere al campo con el nombre en la tabla editorial
                }
               return criteriaBuilder.and(predicados.toArray(new Predicate[predicados.size()]));
            }
        };
        repo.findAll(filtro);
         */


}
