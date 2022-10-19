package com.secondhandmarket.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.secondhandmarket.spring.entidades.Producto;
import com.secondhandmarket.spring.entidades.Usuario;
import com.secondhandmarket.spring.servicio.ProductoServicio;
import com.secondhandmarket.spring.servicio.UsuarioServicio;

@Controller
public class ProductosController {
	
	@Autowired
	ProductoServicio productoServicio;
	
	@Autowired
	UsuarioServicio usuarioServicio;

	private Usuario usuario;
	
	@ModelAttribute("misProductos")
	public List<Producto> misProductos(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = usuarioServicio.buscarPorEmail(email);
		return (productoServicio.productosDeUnPropietario(usuario));
	}
}
