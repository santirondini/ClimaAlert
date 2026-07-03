package ar.edu.utn.frba.ddsi.common.events;


import ar.edu.utn.frba.ddsi.common.dtos.AlertaEventoDTO;
import ar.edu.utn.frba.ddsi.common.services.INotificacionesService;
import ar.edu.utn.frba.ddsi.common.services.impl.NotificacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
@RequiredArgsConstructor
public class AlertaListener {

    @Autowired
    private final INotificacionesService notificacionesService;

    @RabbitListener(queues = "${climaAlert.events.queues.alerta-registrada}")    public void onAlertaReciba(AlertaEventoDTO alertaEventoDTO){
        log.info("Se recibió la alerta: " + alertaEventoDTO.toString());
        try {
            this.notificacionesService.enviarNotificacion(alertaEventoDTO);
            log.info("Se envió la notificación");
        } catch (Exception e) {
            log.error("Error al enviar la notificación", e);
        }
    }

}
