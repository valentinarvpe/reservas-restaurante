package com.backend.apirest.restaurante.modelos.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.apirest.restaurante.modelos.entidades.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
}
