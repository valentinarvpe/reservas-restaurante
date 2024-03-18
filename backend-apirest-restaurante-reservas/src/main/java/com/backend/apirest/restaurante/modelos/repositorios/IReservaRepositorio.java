package com.backend.apirest.restaurante.modelos.repositorios;

import java.util.List;
import java.util.Optional;

import com.backend.apirest.restaurante.modelos.dto.IReservaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.apirest.restaurante.modelos.entidades.Reserva;


@Repository
public interface IReservaRepositorio extends JpaRepository<Reserva, Long> {

	List<IReservaDTO> findAllByEstado(boolean id);

	Optional<IReservaDTO> findReservaById(long id);
}
