package itu.celikelni.ipc;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${smoc.rabbitmq.queue}")
    private String IPC_QUEUE;

    @Value("${smoc.rabbitmq.exchange}")
    private String IPC_EXCHANGE ;

    @Value("${smoc.rabbitmq.routingkey}")
    private String IPC_ROUTING_KEY ;

    @Value("${smoc.rabbitmq.queue_2}")
    private String IPC_QUEUE_2;

    @Value("${smoc.rabbitmq.exchange_2}")
    private String IPC_EXCHANGE_2 ;

    @Value("${smoc.rabbitmq.routingkey_2}")
    private String IPC_ROUTING_KEY_2 ;

    @Bean
    Queue ipcQueue() {
        return new Queue(IPC_QUEUE, false);
    }

    @Bean
    Queue ipcQueue_2() {
        return new Queue(IPC_QUEUE_2, false);
    }

    @Bean
    DirectExchange ipcExchange() {
        return new DirectExchange(IPC_EXCHANGE);
    }

    @Bean
    DirectExchange ipcExchange_2() {
        return new DirectExchange(IPC_EXCHANGE_2);
    }

    @Bean
    Binding binding(Queue ipcQueue, DirectExchange ipcExchange) {
        return BindingBuilder.bind(ipcQueue).to(ipcExchange).with(IPC_ROUTING_KEY);
    }

    @Bean
    Binding binding_2(Queue ipcQueue_2, DirectExchange ipcExchange_2) {
        return BindingBuilder.bind(ipcQueue_2).to(ipcExchange_2).with(IPC_ROUTING_KEY_2);
    }


}
