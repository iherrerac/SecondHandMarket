package com.secondhandmarket.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.secondhandmarket.spring.entidades.Usuario;
import com.secondhandmarket.spring.servicio.UsuarioServicio;
import com.secondhandmarket.spring.upload.StorageService;

@Controller
public class LoginController {
	
	@Autowired
	UsuarioServicio usuarioServicio;
	
	@Autowired
	StorageService storageService;
	
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
	public String registro (@ModelAttribute Usuario usuario,@RequestParam("file") MultipartFile file) { // Inyectamos el usuario
		//Subir imagen Avatar
		if (!file.isEmpty()) {
			String avatar = storageService.store(file);
			usuario.setAvatar(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", avatar).build().toUriString());
		}
			
		if (usuario != null) usuarioServicio.registrar(usuario);
		return "redirect:/auth/login";
	}

}
