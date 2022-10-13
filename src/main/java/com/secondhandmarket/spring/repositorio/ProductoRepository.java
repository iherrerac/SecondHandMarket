package com.secondhandmarket.spring.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secondhandmarket.spring.entidades.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long	>{
	
	// Todos los productos de los cuales somos propietarios
	List<Producto> findByPropietario();
	//Todos los productos de una compra
	List<Producto> findByCompra();
	// Todos los productos que esten todavia en venta y no hayamos comprado
	List<Producto> findByCompraIsNull();
	//Producto por nombre que no hayamos comprado
	List<Producto> findByNombreContainsIgnoreCaseAndCompraIsNull();
	//Producto por nombre que hayamos comprado
	List<Producto> findByNombreContainsIgnoreCaseAndPropietario();
}
