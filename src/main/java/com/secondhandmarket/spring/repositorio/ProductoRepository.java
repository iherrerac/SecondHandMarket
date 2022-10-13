package com.secondhandmarket.spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secondhandmarket.spring.entidades.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
