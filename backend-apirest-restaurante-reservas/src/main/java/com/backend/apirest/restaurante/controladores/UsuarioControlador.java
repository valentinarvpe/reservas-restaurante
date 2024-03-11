package com.backend.apirest.restaurante.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.backend.apirest.restaurante.modelos.entidades.Usuario;
import com.backend.apirest.restaurante.modelos.servicios.UsuarioServicio;

@CrossOrigin(origins = "http://localhost:3000", originPatterns = "*")
@RestController
@RequestMapping("/api/users")
public class UsuarioControlador {

	@Autowired
	UsuarioServicio usuarioServicio;

	@GetMapping
	public List<Usuario> listar() {
		return usuarioServicio.traeUsuarios();
	}

	@PostMapping("/registrar")
	public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario){
		usuario.setAdmin(false);
		return create(usuario);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioServicio.guardar(usuario));
	}
}
