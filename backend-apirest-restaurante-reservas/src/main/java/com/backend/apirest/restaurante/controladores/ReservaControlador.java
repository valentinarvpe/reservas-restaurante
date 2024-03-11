package com.backend.apirest.restaurante.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.apirest.restaurante.modelos.entidades.Reserva;
import com.backend.apirest.restaurante.modelos.servicios.ReservaServicio;

@CrossOrigin
@RestController
@RequestMapping("/api/reservas")
public class ReservaControlador {

	@Autowired
	private ReservaServicio reservaServicio;

	@GetMapping
	public List<Reserva> traeReservas() {
		return reservaServicio.traerReservas();
	}
	
	@GetMapping("/porEstado")
	public List<Reserva> traeReservas(@RequestParam boolean estado) {
		return reservaServicio.traerReservasPorEstado(estado);
	}
	
	@PostMapping
	public ResponseEntity<String> guardarReserva(@RequestBody Reserva reserva) {
		try {
			reservaServicio.guardar(reserva);	
			return ResponseEntity.ok("Guardado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Error al guardar");
		}
			
	}
	
	@PutMapping("/{id}")
	public void actualizarReserva(@RequestBody Reserva reserva, @PathVariable Long id) {
		try {
			Reserva reservaExistente =  reservaServicio.traerPorId(id);
			if (reservaExistente.getId() != null) {
				reservaServicio.guardar(reserva);	
			} else {
				System.out.println("No existe");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/actualizaEstado/{id}")
	public void actualizarEstadoReserva(@PathVariable Long id, @RequestParam boolean estado) {
		try {
			Reserva reservaExistente =  reservaServicio.traerPorId(id);
			reservaExistente.setEstado(estado);
			reservaServicio.guardar(reservaExistente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
