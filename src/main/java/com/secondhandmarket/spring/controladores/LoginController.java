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
	
	@GetMapping("/")
	public String welcome() {
		return "redirect:/public/";
	}
	
	//show del formulario de login
	//el formulario de login y de registro es el mismo
	//al llamar a Login creamos un nuevo usuario para la parte de "register"
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario()); //Commandobject
		return "login";
	}
	
	//Le pasamos el usuario creado al registro
	@PostMapping("/auth/register")
	public String registro (@ModelAttribute Usuario usuario) { // Inyectamos el usuario
		if (usuario != null) usuarioServicio.registrar(usuario);
		return "redirect:/auth/login";
	}

}
