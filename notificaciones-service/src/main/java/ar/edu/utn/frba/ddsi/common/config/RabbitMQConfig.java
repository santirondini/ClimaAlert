package ar.edu.utn.frba.ddsi.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Queue;

public class RabbitMQConfig {

    @Value("${climaAlert.events.exchange}")
    private String eventosExhangeName;

    @Value("${climaAlert.events.routing-keys.alerta-registrada}")
    private String alertaRegistradaRoutingKey;

    @Value("${climaAlert.events.queues.alerta-registrada}")
    private String alertaRegistradaQueueName;

    @Bean
    public DirectExchange eventosExchange() {
        return new DirectExchange(eventosExchangeName, true, false);
    }

    @Bean
    public Queue ventaRegistradaQueue() {
        return QueueBuilder.durable(ventaRegistradaQueueName).build();
    }

    @Bean
    public Binding alertaRegistradaBinding(Queue alertaRegistradaQueue, DirectExchange eventosExchange) {
        return BindingBuilder.bind(alertaRegistradaQueue).to(eventosExchange).with(alertaRegistradaRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

}
