package ar.edu.utn.frba.ddsi.common.services;

import ar.edu.utn.frba.ddsi.common.dtos.AlertaEventoDTO;

public interface INotificacionesService{
    void enviarNotificacion(AlertaEventoDTO alertaEventoDTO);
}
