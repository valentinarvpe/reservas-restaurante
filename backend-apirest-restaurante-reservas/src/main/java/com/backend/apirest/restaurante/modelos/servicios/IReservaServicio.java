package com.backend.apirest.restaurante.modelos.servicios;

import java.util.List;
import java.util.Optional;

import com.backend.apirest.restaurante.modelos.dto.IReservaDTO;
import com.backend.apirest.restaurante.modelos.dto.ReservaDto;
import org.springframework.stereotype.Service;

import com.backend.apirest.restaurante.modelos.entidades.Reserva;

@Service
public interface IReservaServicio {
	
	public List<Reserva> traerReservas();

	public List<IReservaDTO> traerReservasPorEstado(boolean estado);
	
	//List<Reserva> traerReservasPorEstado(boolean estado);
	
	public Reserva traerPorId(Long id);

	public IReservaDTO traePorID(long id);
	
	public Reserva guardar(Reserva reserva);
	
	public void eliminar(Long id);


}
