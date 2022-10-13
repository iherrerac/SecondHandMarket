package com.secondhandmarket.spring.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secondhandmarket.spring.entidades.Compra;
import com.secondhandmarket.spring.entidades.Usuario;

public interface CompraRepository extends JpaRepository<Compra, Long>{
	
	//Buscar todas las compras de un propietario
	List<Compra> findByPropietario(Usuario propietario);

}
