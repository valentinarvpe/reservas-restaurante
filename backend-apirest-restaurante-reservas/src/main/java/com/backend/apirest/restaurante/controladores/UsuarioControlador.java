package com.backend.apirest.restaurante.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.apirest.restaurante.modelos.entidades.Usuario;
import com.backend.apirest.restaurante.modelos.servicios.UsuarioServicio;

@CrossOrigin(origins = "http://localhost:3000", originPatterns = "*")
@RestController
public class UsuarioControlador {

	@Autowired
	UsuarioServicio usuarioServicio;

	@PostMapping("login")
	public ResponseEntity<?> ingresar(@RequestBody Usuario usuario) {
		Map<String, Object> resp = new HashMap<>();

		try {
			Usuario usuarioExistente = usuarioServicio.traePorEmail(usuario.getEmail());
			String rol_name = usuarioExistente.getId_rol() == 1 ? "ADMIN" : "USER";
			if (usuarioExistente.getClave().equals(usuario.getClave())) {
				resp.put("rol", rol_name);
				resp.put("usuario", usuarioExistente.getEmail());
				resp.put("mensaje", "Ok");
			}
			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.OK);
		} catch (Exception e) {
			resp.put("mensaje", "No puede ingresar");
			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.UNAUTHORIZED);
		}

	}

}
