package com.backend.apirest.restaurante.modelos.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotEmpty
    @Size(min=2, max=100)
    @Column(name = "nombres")
    private String nombres;

    @NotEmpty
    @Size(min=2, max=100)
    @Column(name = "apellidos")
    private String apellidos;

    @NotEmpty
    @Size(min=2, max=10)
    @Column(name = "tipo_documento")
    private String tipo_documento;

    @NotEmpty
    @Size(min=2, max=20)
    @Column(name = "identificacion")
    private String identificacion;

    @NotEmpty
    @Size(min=2, max=100)
    @Column(name = "email")
    private String email;

    @Column(name = "fecha_reserva")
	@Temporal(TemporalType.DATE)
    private LocalDate fecha_reserva;

    @NotEmpty
    @Size(min=2, max=60)
    @Column(name = "tipo_reserva")
    private String tipo_reserva;

    @NotNull
    @Column(name = "cantidad_personas")
    private Integer cantidad_personas = 0;

    @NotEmpty
    @Size(min=2, max=300)
    @Column(name = "observaciones")
    private String observaciones;
    
    @Column(name = "estado")
    private boolean estado;

}
