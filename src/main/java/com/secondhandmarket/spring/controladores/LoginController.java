package com.secondhandmarket.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.secondhandmarket.spring.entidades.Usuario;
import com.secondhandmarket.spring.servicio.UsuarioServicio;

@Controller
public class LoginController {
	
	@Autowired
	UsuarioServicio usuarioServicio;
	
	//Redirige la barra al listado de productos
	@GetMapping("/")
	public String welcome() {
		return "redirect:/public/";
	}
	
	//El show del formulario de login
	@GetMapping("/auth/login")
	public String login(Model model) {
		//Es tambien formulario de registro
		model.addAttribute("usuario", new Usuario());
		return ("/login/");
	}
	
	//PostMapping, no del login, que esta dentro del circuito de seguridad, si no del usuario registrado antes
	@PostMapping ("/auth/register")
	public String register (@ModelAttribute Usuario usuario) {
		usuarioServicio.registrar(usuario);
		return "redirect:/auth/login";
	}

}
