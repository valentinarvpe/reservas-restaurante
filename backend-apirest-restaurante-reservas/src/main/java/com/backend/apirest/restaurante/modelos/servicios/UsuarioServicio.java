package com.backend.apirest.restaurante.modelos.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend.apirest.restaurante.modelos.entidades.Rol;
import com.backend.apirest.restaurante.modelos.repositorios.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.backend.apirest.restaurante.modelos.entidades.Usuario;
import com.backend.apirest.restaurante.modelos.repositorios.IUsuarioRepository;

@Service
public class UsuarioServicio implements IUsuarioServicio{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private IRolRepository rolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


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
			Optional<Rol> OptionalRolUsuario = rolRepository.findByNombre("ROLE_USER");
			Optional<Rol> OptionalRolCliente = rolRepository.findByNombre("ROLE_CLIENT");
			List<Rol> roles = new ArrayList<>();
			OptionalRolUsuario.ifPresent(roles::add);
			OptionalRolCliente.ifPresent(roles::add);

			if (usuario.isAdmin()) {
				Optional<Rol> optionalRolAdmin =rolRepository.findByNombre("ROLE_ADMIN");
				optionalRolAdmin.ifPresent(roles::add);
				String contrasenaCodificada = passwordEncoder.encode(usuario.getClave());
				usuario.setClave(contrasenaCodificada);
			}

			usuario.setRoles(roles);
			return usuarioRepository.save(usuario);

	}

	@Override
	public Usuario traePorEmail(String email) {
		return usuarioRepository.findByEmail(email).orElse(null);
	}

	@Override
	public Usuario traePorEmaileIdentificacion(String email, String identificacion) {
		return usuarioRepository.findByEmailAndIdentificacion(email, identificacion).orElse(null);
	}


}
