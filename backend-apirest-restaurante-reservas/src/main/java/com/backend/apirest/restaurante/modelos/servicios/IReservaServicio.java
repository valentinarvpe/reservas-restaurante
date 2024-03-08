package com.backend.apirest.restaurante.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.apirest.restaurante.modelos.entidades.Reserva;

@Service
public interface IReservaServicio {
	
	public List<Reserva> traerReservas();
	
	List<Reserva> traerReservasPorEstado(boolean estado);
	
	public Reserva traerPorId(Long id);
	
	public Reserva guardar(Reserva reserva);
	
	public void eliminar(Long id);


}
