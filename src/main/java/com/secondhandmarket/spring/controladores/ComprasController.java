package com.secondhandmarket.spring.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.secondhandmarket.spring.entidades.Compra;
import com.secondhandmarket.spring.entidades.Producto;
import com.secondhandmarket.spring.entidades.Usuario;
import com.secondhandmarket.spring.servicio.CompraServicio;
import com.secondhandmarket.spring.servicio.ProductoServicio;
import com.secondhandmarket.spring.servicio.UsuarioServicio;

@Controller
@RequestMapping("/app") //Zona privada
public class ComprasController {

		@Autowired
		UsuarioServicio usuarioServicio;
		
		@Autowired
		ProductoServicio productoServicio;
		
		@Autowired
		CompraServicio compraServicio;
		
		//Sesion para poder implementar el carrito
		@Autowired
		HttpSession session;
	
		//Atributo privado para el usuario que esta logeado, pues solo tenemos una parte de usuario logeado, 
		//no una instancia completa. Vamos a necesitarlo completo para vincular la compra
		private Usuario usuario;
	
		//Carrito siempre disponible. El Carrito es un listado de los id que nos disponemos a comprar
		@ModelAttribute("carrito")
		public List<Producto> productosCarrito(){
			List<Long> contenido = (List<Long>) session.getAttribute("carrito");
			return (contenido == null)? null :productoServicio.variosPorId(contenido);
		}
	
		//Coste total del carrito
		@ModelAttribute("total_carrito")
		public Double totalCarrito() {
			List<Producto> productosCarrito = productosCarrito();
			if (productosCarrito != null) {
				return productosCarrito.stream()
				.mapToDouble(p -> p.getPrecio()).sum();				
			}
			return 0.0;
		}

		//Todas las compras realizadas por nosotros
		@ModelAttribute("mis_compras")
		public List<Compra> misCompras(){
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			usuario = usuarioServicio.buscarPorEmail(email);
			return compraServicio.porPropietario(usuario);
			}

		//Visualizar el carrito. Como tenemos disponibles los modelos de "carrito" y "totalcompra", solo redirigimos a la plantilla carrito
		@GetMapping("/carrito")
		public String verCarrito (Model model) {
			return "/app/compra/carrito";
		}
	
		//Añadir producto al carrito. En base al id del producto, Sacamos el carrito desde la session. Si existe el carrito
		// nos lo devuelve si no lo creamos. Comprobamos si el id esta contenido y si no lo añadimos y almacenamos, 
		// y nos vamos a visualizarlo
		@GetMapping("/carrito/add/{id}")
		public String addCarrito(Model model, @PathVariable Long id) {
			List<Long> contenido = (List<Long>) session.getAttribute("carrito");
			if (contenido == null) contenido = new ArrayList<>(); //Creamos carrito en blanco
			if (!contenido.contains(id))
				contenido.add(id); //añadimos el producto
			session.setAttribute("carrito", contenido); //seteamos el nuevo carrito
			return "redirect:/app/carrito";
		}
		
		
		
	//Eliminar carrito. Si alguien esta intentando eliminar un producto y no lo tiene, volvemos al listado de productos
	//Si no lo eliminamos. Si el carrito se queda vacio eliminamos el carrito. 
	///si no lo esta almacenamos de nuevo el carrito en la sesion y volvemos a la pagina del carrito
		@GetMapping("/carrito/eliminar/{id}")
		public String deleteCarrito(Model model, @PathVariable Long id) {
			List<Long> contenido = (List<Long>) session.getAttribute("carrito");
			if (contenido == null) return "redirect:/public";//delete improcedente al no haber carrito, redirijimos
			if (contenido.contains(id)) 
				contenido.remove(id);//delete
			if (contenido.isEmpty())
				session.removeAttribute("carrito");
			else
				session.setAttribute("carrito", contenido); //seteamos el nuevo carrito
			return "redirect:/app/carrito";
		}
	
		/**
		 * Checkout del carrito: en ruta carrito/finalizar. si es nulo ir a listado de productos.
		 *si no cogemos el listado deproductos insertamos en una nueva compra y 
		 *asignamos a cada producto la compra en la que estan. Eliminamos carrito 
		 *y nos redirigimos a la factura+id */
		@GetMapping("/carrito/finalizar")
		public String checkout() {
			List<Long> contenido = (List<Long>) session.getAttribute("carrito");
			if(contenido == null) return "redirect:/public";
			List<Producto> productos = productosCarrito();
			Compra c = compraServicio.insertar(new Compra(), usuario);
			productos.forEach(p->compraServicio.addProductoCompra(p, c));
			session.removeAttribute("carrito");
			return "redirect:/app/compra/factura/"+c.getId();
		}

		
		/**
		 * Una factura en base al id, tendrá que Buscar la compra , sacar la factura de esa compra
		 * y mostrar el listado de esos productos con el precio de cada uno y el precio total.
		 * asignamos al modelo y nos redirigimos a la vista factura 
		 */
		@GetMapping("/compra/factura/{id}")
		public String factura(Model model, @PathVariable Long id) {
			Compra c = compraServicio.buscarPorId(id);
			List<Producto> productos = productoServicio.productosDeUnaCompra(c);
			model.addAttribute("productos",productos);
			model.addAttribute("compra",c);
			model.addAttribute("total_compra",productos.stream().mapToDouble(p-> p.getPrecio()).sum());
			return "/app/compra/factura";
		}
		
		
		/**
		 * Mis compras: Tenemos el modelo mis compras y simplemente tenemos que ir a la plantilla correspondiente
		 * para verlo
		 */
		
		@GetMapping("/miscompras")
		public String misCompras(Model model) {
			return "/app/compra/listado";
		}
}
