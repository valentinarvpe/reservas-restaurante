package com.backend.apirest.restaurante.modelos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class ReservaDto implements Serializable {

     long id;
     LocalDate fechaReserva;
     String tipoReserva;
     Integer cantidadPersonas;
     String observaciones;
     boolean estado;

}
