package ar.edu.utn.frba.ddsi.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange eventosExchange(@Value("${climaAlert.events.exchange}") String name) {
        return new DirectExchange(name, true, false);
    }

    @Bean
    public Queue alertaRegistradaQueue(@Value("${climaAlert.events.queues.alerta-registrada}") String name) {
        return QueueBuilder.durable(name).build();
    }

    @Bean
    public Binding alertaRegistradaBinding(Queue alertaRegistradaQueue,
                                           DirectExchange eventosExchange,
                                           @Value("${climaAlert.events.routing-keys.alerta-registrada}") String ruta) {
        return BindingBuilder.bind(alertaRegistradaQueue).to(eventosExchange).with(ruta);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

}
