package itu.celikelni.ipc;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Publisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${smoc.rabbitmq.exchange}")
    private String IPC_EXCHANGE ;

    @Value("${smoc.rabbitmq.exchange_2}")
    private String IPC_EXCHANGE_2 ;

    @Value("${smoc.rabbitmq.routingkey}")
    private String IPC_ROUTING_KEY ;

    @Value("${smoc.rabbitmq.routingkey_2}")
    private String IPC_ROUTING_KEY_2;

    public void send(Message msg) {
        System.out.println("Publisher::send()");
        rabbitTemplate.convertAndSend(IPC_EXCHANGE,IPC_ROUTING_KEY, msg);
        //String reply = rabbitTemplate.convertSendAndReceive(IPC_EXCHANGE,IPC_ROUTING_KEY, msg).toString();
    }

    public void send_2(Message msg) {
        System.out.println("Publisher::send_2()");
        rabbitTemplate.convertAndSend(IPC_EXCHANGE_2,IPC_ROUTING_KEY_2, msg);
    }
}
