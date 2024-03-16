package com.backend.apirest.restaurante.modelos.repositorios;

import com.backend.apirest.restaurante.modelos.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
