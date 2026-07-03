package ar.edu.utn.frba.ddsi.common.services.impl;

import ar.edu.utn.frba.ddsi.common.dtos.AlertaEventoDTO;
import ar.edu.utn.frba.ddsi.common.models.entities.MedioNotificacion;
import ar.edu.utn.frba.ddsi.common.models.entities.Notificacion;
import ar.edu.utn.frba.ddsi.common.services.INotificacionesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NotificacionesService implements INotificacionesService {

    private MedioNotificacion medioNotificacion;

    public List<String> mails = List.of(
            "admin@clima.com",
            "emergencias@clima.com",
            "meteorologia@clima.com"
    );

    private Notificacion alertaToNotificacion(AlertaEventoDTO alertaEventoDTO){
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(alertaEventoDTO.getMensaje());
        notificacion.setFecha(java.time.LocalDate.now());
        return notificacion;
    }

    @Override
    public void enviarNotificacion(AlertaEventoDTO alertaEventoDTO) {
        log.info("Enviando notificación para la alerta: " + alertaEventoDTO.toString());
        Notificacion notificacion = this.alertaToNotificacion(alertaEventoDTO);
        try {
            this.medioNotificacion.enviarNotificacion(notificacion);
            log.info("Se enviaron correctamente los mails");
        } catch (Exception e) {
            log.error("Error al enviar notificación", e);
        }
    }
}
