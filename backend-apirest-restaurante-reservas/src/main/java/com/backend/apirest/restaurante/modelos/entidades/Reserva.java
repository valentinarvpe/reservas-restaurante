package com.backend.apirest.restaurante.modelos.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reservas")
public class Reserva implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombres")
    private String nombres;
    
    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "tipo_documento")
    private String tipo_documento;

    @Column(name = "identificacion")
    private String identificacion;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "fecha_reserva")
	@Temporal(TemporalType.DATE)
    private Date fecha_reserva;
    
    @Column(name = "tipo_reserva")
    private String tipo_reserva;
    
    @Column(name = "cantidad_personas")
    private String cantidad_personas;
    
    @Column(name = "observaciones")
    private String observaciones;
    
    @Column(name = "estado")
    private Boolean estado;

}
