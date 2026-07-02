package ar.edu.utn.frba.ddsi.common.events;


import ar.edu.utn.frba.ddsi.common.dtos.AlertaEventoDTO;
import ar.edu.utn.frba.ddsi.common.models.entities.RegistroClima;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlertaEventoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private String exchange;
    private String routingKey;

    public AlertaEventoPublisher(RabbitTemplate rabbitTemplate, @Value("${climaAlert.events.exchange}") String exchange, @Value("${climaAlert.events.routing-keys.clima-alerta}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void publicarAlerta(RegistroClima registroClima) {
        AlertaEventoDTO alertaEventoDTO = this.armadoDeAlerta(registroClima);
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, alertaEventoDTO);
            log.info("Se publicó la alerta");
        } catch (AmqpException e) {
            log.error("Error al publicar la alerta", e);
        }
    }

    private AlertaEventoDTO armadoDeAlerta(RegistroClima registroClima){
        AlertaEventoDTO alerta = new AlertaEventoDTO();
        alerta.setUbicacion(registroClima.getUbicacion());
        alerta.setTemperatura(registroClima.getTemperatura());
        alerta.setHumedad(registroClima.getHumedad());
        alerta.setMensaje("Se ha detectado una alerta climática en " + registroClima.getUbicacion() +
                " con temperatura de " + registroClima.getTemperatura() +
                "°C y humedad de " + registroClima.getHumedad() + "%.");
        return alerta;
    }


}
