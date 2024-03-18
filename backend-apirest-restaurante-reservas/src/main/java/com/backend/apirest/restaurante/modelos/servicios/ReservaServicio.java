package com.backend.apirest.restaurante.modelos.servicios;

import java.util.List;

import com.backend.apirest.restaurante.modelos.dto.IReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.apirest.restaurante.modelos.entidades.Reserva;
import com.backend.apirest.restaurante.modelos.repositorios.IReservaRepositorio;

@Service
public class ReservaServicio implements IReservaServicio{

	@Autowired
	private IReservaRepositorio reservaRepository;

	@Override
	@Transactional(readOnly = true )
	public List<Reserva> traerReservas() {
		List<Reserva> reservasEntidad = reservaRepository.findAll();
		return reservasEntidad;
	}

	@Override
	public List<IReservaDTO> traerReservasPorEstado(boolean estado) {
		return reservaRepository.findAllByEstado(estado);
	}

	
	@Override
	public Reserva traerPorId(Long id) {
		return reservaRepository.findById(id).orElse(null);
	}

	@Override
	public IReservaDTO traePorID(long id) {
		return reservaRepository.findReservaById(id).orElse(null);
	}

	@Override
	public Reserva guardar(Reserva reserva) {
		return reservaRepository.save(reserva);
	}
	
	@Override
	public void eliminar(Long id) {
		reservaRepository.deleteById(id);
	}

}
