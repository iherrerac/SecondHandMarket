package com.secondhandmarket.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.secondhandmarket.spring.entidades.Producto;
import com.secondhandmarket.spring.servicio.ProductoServicio;

@Controller
@RequestMapping("/public")
public class ZonaPublicaController {
	
	@Autowired
	ProductoServicio productoServicio;
	
	@ModelAttribute("productos")
	public List<Producto> ProductosNoVendidos(){
		return productoServicio.productosSinVender();
	}

	@GetMapping({"/","/index"})
	public String index (Model model, @RequestParam(name = "q", required = false) String query) {
		if (query != null) //Si la query es nula tirara de productosSinVender
			model.addAttribute("productos", productoServicio.buscar(query));
		return "index";
	}
	
	@GetMapping("/producto/{id}")
	public String showProducto (Model model, @PathVariable Long id) {
		Producto result = productoServicio.findById(id);
		if(result != null) {
			model.addAttribute("producto", result);
			return ("producto");
		}
		
		return ("redirect:/public");
		
	}
}
