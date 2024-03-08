package com.backend.apirest.restaurante.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.apirest.restaurante.modelos.entidades.Usuario;
import com.backend.apirest.restaurante.modelos.repositorios.IUsuarioRepository;

@Service
public class UsuarioServicio implements IUsuarioServicio{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> traeUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario traerPorId(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public Usuario guardar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario traePorEmail(String email) {
		return usuarioRepository.findByEmail(email).orElse(null);
	}
	


}
