package com.secondhandmarket.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.secondhandmarket.spring.upload.StorageService;

/*
 * Como vamos a trabajar con ficheros en dos controladores distintos 
 * (ProductoController para subir la imagen del fichero y LoginController para el avatar del usuario), 
 * el metodo serveFile Lo pondremos en un controlador distino.
 */

@Controller
public class FilesController {

	@Autowired
	StorageService storageService;
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().body(file);
	}
}
