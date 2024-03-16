package com.backend.apirest.restaurante.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.apirest.restaurante.modelos.entidades.Usuario;

@Service
public interface IUsuarioServicio {
	public List<Usuario> traeUsuarios();
	
	public Usuario traerPorId(Long id);
	
	public Usuario guardar(Usuario usuario);
	
	public Usuario traePorEmail(String email);

	public List<Usuario> traeClientes();

	public Usuario traePorEmaileIdentificacion(String email, String identificacion);
}
