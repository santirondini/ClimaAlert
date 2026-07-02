package ar.edu.utn.frba.ddsi.common.events;


import ar.edu.utn.frba.ddsi.common.services.impl.NotificacionesService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AlertaListener {

    private final NotificacionesService notificacionesService;

    public AlertaListener(NotificacionesService notificacionesService) {
        this.notificacionesService = notificacionesService;
    }




}
