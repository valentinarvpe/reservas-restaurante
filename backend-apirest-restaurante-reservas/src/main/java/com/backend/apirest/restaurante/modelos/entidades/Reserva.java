package com.backend.apirest.restaurante.modelos.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;
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

    @Column(name = "fecha_reserva")
	@Temporal(TemporalType.DATE)
    private LocalDate fechaReserva;

    @NotEmpty
    @Size(min=2, max=60)
    @Column(name = "tipo_reserva")
    private String tipoReserva;

    @NotNull
    @Column(name = "cantidad_personas")
    private Integer cantidadPersonas = 0;

    @NotEmpty
    @Size(min=2, max=300)
    @Column(name = "observaciones")
    private String observaciones;
    
    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(
            name = "id_usuario",
            referencedColumnName = "id"
    )
    private Usuario usuario;


}
