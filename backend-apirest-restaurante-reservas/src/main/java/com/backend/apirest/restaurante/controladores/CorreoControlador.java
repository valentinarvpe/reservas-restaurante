package com.backend.apirest.restaurante.controladores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.backend.apirest.restaurante.modelos.entidades.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.apirest.restaurante.modelos.servicios.CorreoService;

import jakarta.mail.MessagingException;

@CrossOrigin(origins = "http://localhost:3000", originPatterns = "*")
@RestController
@RequestMapping("/api/correo")
public class CorreoControlador {

	@Autowired
	CorreoService correoServicio;

	@PostMapping("/enviar")
	public ResponseEntity<String> enviar(@RequestBody Reserva reserva) throws MessagingException {
		try {
			correoServicio.sendEmailFromTemplate(reserva);
			return ResponseEntity.ok("Enviado correctamente");
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.internalServerError().body("Error al enviar correo");
		}

	}


	@GetMapping("/images/{imageName}")
	public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
 
		ClassPathResource resource = new ClassPathResource("templates/" +imageName +".png");

		File file = resource.getFile();
		String imagePath = file.getAbsolutePath();

		 Path imageRuta = Paths.get(imagePath);
		 
		 Resource resource2 = new UrlResource(imageRuta.toUri());

	        if (resource2.exists() && resource2.isReadable()) {
	            return ResponseEntity.ok()
	                .contentType(MediaType.IMAGE_PNG)
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                		"inline; filename=\"" + imageName + "\"")
	                .body(resource);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
		 
	}
}
