package com.secondhandmarket.spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secondhandmarket.spring.entidades.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

}
