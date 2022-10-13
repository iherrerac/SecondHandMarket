package com.secondhandmarket.spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secondhandmarket.spring.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
