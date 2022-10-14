package com.secondhandmarket.spring.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secondhandmarket.spring.entidades.Compra;
import com.secondhandmarket.spring.entidades.Producto;
import com.secondhandmarket.spring.entidades.Usuario;

public interface ProductoRepository extends JpaRepository<Producto, Long	>{
	
	// Todos los productos de los cuales somos propietarios
	List<Producto> findByPropietario(Usuario propietario);
	//Todos los productos de una compra
	List<Producto> findByCompra(Compra compra);
	// Todos los productos que esten todavia en venta y no hayamos comprado
	List<Producto> findByCompraIsNull();
	//Producto por nombre que no hayamos comprado
	List<Producto> findByNombreContainsIgnoreCaseAndCompraIsNull(String nombre);
	//Producto por nombre que hayamos comprado
	List<Producto> findByNombreContainsIgnoreCaseAndPropietario(String nombre, Usuario propietario);
}
