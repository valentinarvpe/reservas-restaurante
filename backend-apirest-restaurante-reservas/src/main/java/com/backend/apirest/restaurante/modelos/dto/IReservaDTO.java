package com.backend.apirest.restaurante.modelos.dto;

import java.time.LocalDate;

public interface IReservaDTO {

    Integer getId();
    LocalDate getFechaReserva();
    String getTipoReserva();
    String getObservaciones();
    Integer getCantidadPersonas();
    Boolean getEstado();

    IUsuarioDTO getUsuario();

}
