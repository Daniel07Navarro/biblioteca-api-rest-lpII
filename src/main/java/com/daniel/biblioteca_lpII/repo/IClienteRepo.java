package com.daniel.biblioteca_lpII.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daniel.biblioteca_lpII.model.Cliente;

import java.util.List;
import java.util.Map;

@Repository
public interface IClienteRepo extends IGenericRepo<Cliente, Integer> {

    Cliente findOneByNombreCompleto(String nombre);

    Cliente findOneByEmail(String email);

    /*@Query(value = "select sum(v.total),sum(v.impuesto),li.titulo, sum(vd.cantidad)\n" +
            "from cliente cli,venta v, venta_detalle vd, libro li \n" +
            "where cli.id_cliente = v.id_cliente and v.id_venta = vd.id_venta and vd.id_libro= li.id_libro and cli.id_cliente=:id\n" +
            "group by li.titulo",nativeQuery = true)
     */

    @Query(value = """
            select li.titulo Titulo,v.total Total,v.impuesto Impuesto ,vd.precio  Precio ,vd.cantidad  Cantidad , DATE_FORMAT(v.fecha_venta, '%Y-%m-%d')  Fecha_Compra
            from cliente cli,venta v, venta_detalle vd, libro li
            where cli.id_cliente = v.id_cliente 
            and v.id_venta = vd.id_venta and vd.id_libro= li.id_libro 
            and cli.id_cliente= :id order by v.fecha_venta desc 
            """, nativeQuery = true)
    List<Map<String, Object>> misDetalles(@Param("id") Integer id);
    //List<Object> misDetalles(@Param("id") Integer id);

    @Query(value = """
            select v.id_venta AS idVenta, v.total AS total, v.impuesto AS impuesto, vd.cantidad AS Cantidad, li.titulo AS Titulo, li.autor AS Autor, 
            DATE_FORMAT(v.fecha_venta, '%Y-%m-%d %H:%i') AS Fecha_Compra,
                vd.precio AS Precio, li.imagen, vd.id_venta_detalle
            from cliente cli
                inner join venta v ON cli.id_cliente = v.id_cliente
                inner join venta_detalle vd ON v.id_venta = vd.id_venta
                inner join libro li ON vd.id_libro = li.id_libro where cli.id_cliente = :id
            """, nativeQuery = true)
    List<Object[]> misDetallesV2(@Param("id") Integer id);


}
