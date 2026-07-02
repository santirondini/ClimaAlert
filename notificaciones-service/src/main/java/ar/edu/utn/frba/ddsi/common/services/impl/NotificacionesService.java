package ar.edu.utn.frba.ddsi.common.services.impl;

import ar.edu.utn.frba.ddsi.common.dtos.AlertaEventoDTO;
import ar.edu.utn.frba.ddsi.common.models.entities.MedioNotificacion;
import ar.edu.utn.frba.ddsi.common.models.entities.Notificacion;
import ar.edu.utn.frba.ddsi.common.services.INotificacionesService;

public class NotificacionesService implements INotificacionesService {

    private MedioNotificacion medioNotificacion;

    private Notificacion alertaToNotificacion(AlertaEventoDTO alertaEventoDTO){
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(alertaEventoDTO.getMensaje());
        notificacion.setFecha(java.time.LocalDate.now());
        return notificacion;
    }

    @Override
    public void enviarNotificacion(AlertaEventoDTO alertaEventoDTO) {
        Notificacion notificacion = this.alertaToNotificacion(alertaEventoDTO);
        this.medioNotificacion.enviarNotificacion(notificacion);
    }
}
