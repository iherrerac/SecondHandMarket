package com.secondhandmarket.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.secondhandmarket.spring.entidades.Producto;
import com.secondhandmarket.spring.entidades.Usuario;
import com.secondhandmarket.spring.servicio.ProductoServicio;
import com.secondhandmarket.spring.servicio.UsuarioServicio;

@Controller
@RequestMapping("/app")
public class ProductosController {
	
	@Autowired
	ProductoServicio productoServicio;
	
	@Autowired
	UsuarioServicio usuarioServicio;

	private Usuario usuario;
	
	//TEner siempre disponible mis productos
	@ModelAttribute("misproductos")
	public List<Producto> misProductos(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = usuarioServicio.buscarPorEmail(email);
		return (productoServicio.productosDeUnPropietario(usuario));
	}
	
	/*
	 * Posibilidad de hacer consultas. SI la consulta esta vacia Diretamente tiraremos del modelo "misProductos",
	 *  si no mostraremos el producto buscado
	 */
	@GetMapping("/misproductos")
	public String listaMisProductos(Model model, @RequestParam(name = "q", required = false) String query) {
		if (query != null) model.addAttribute("misproductos", productoServicio.buscarMisProductos(query, usuario)); 
		return "/app/producto/lista";
	}
	
	/*
	 * Eliminar uno de mis productos: le pasamos el id, lo recogeria, buscaria el producto completo, 
	 * si no esta comprado lo eliminaria, y nos redirigiria a mis productos 
	 */
	
	@GetMapping("/misproductos/{id}/eliminar")
	public String eliminar(Model model, @PathVariable Long id) {
		Producto p = productoServicio.findById(id);
		if (p.getCompra() == null) productoServicio.borrar(p);  
		return "redirect:/app/misproductos";
	}
	
	/*
	 * Mapear la opcion nuevo producto, Crea el nuevo producto
	 */
	
	@GetMapping("/producto/nuevo")
	public String nuevoProductoForm(Model model) {
		model.addAttribute("producto", new Producto());
		return "/app/producto/form";
	}
	
	/*
	 * Grabar un nuevo producto: Asignarle el usuario como `propietario e insertarlo
	 */

	@PostMapping("/producto/nuevo/submit")
	public String nuevoProducto(@ModelAttribute Producto producto) {
		producto.setPropietario(usuario);
		productoServicio.insertar(producto);
		return "redirect:/app/misproductos";
	}
}
