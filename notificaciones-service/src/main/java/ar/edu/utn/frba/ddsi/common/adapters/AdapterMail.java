package ar.edu.utn.frba.ddsi.common.adapters;

import ar.edu.utn.frba.ddsi.common.models.entities.MedioNotificacion;
import ar.edu.utn.frba.ddsi.common.models.entities.Notificacion;

public class AdapterMail implements MedioNotificacion {

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        // Implementación para enviar notificación por correo electrónico
        System.out.println("Enviando notificación por correo electrónico: " + notificacion);
    }
}
