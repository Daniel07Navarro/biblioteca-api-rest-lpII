package com.daniel.biblioteca_lpII.service;

import com.daniel.biblioteca_lpII.model.Cliente;
import com.daniel.biblioteca_lpII.model.Libro;
import com.daniel.biblioteca_lpII.model.Venta;
import com.daniel.biblioteca_lpII.model.VentaDetalle;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public interface IVentaService extends ICRUD<Venta,Integer>{

	//METODOS DISTINTOS A LOS DEL CRUD

    public List<Libro> getMostSellerProduct();

    public Optional<Venta> ventaMasAntigua();

    public Map<String,Double> clienteConMasCompras() throws Exception;

    public List<Venta> findByIdCliente(Integer id) throws Exception;

}
