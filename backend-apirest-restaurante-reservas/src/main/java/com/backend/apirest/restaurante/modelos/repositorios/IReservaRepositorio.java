package com.backend.apirest.restaurante.modelos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.apirest.restaurante.modelos.entidades.Reserva;

@Repository
public interface IReservaRepositorio extends JpaRepository<Reserva, Long> {

	List<Reserva> findAllByEstado(boolean estado);
}
