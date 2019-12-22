package itu.celikelni.ipc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Subscriber {

    static final Logger logger = LoggerFactory.getLogger(Subscriber.class);
    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = "${smoc.rabbitmq.queue}")
    public void process(Message msg) {
        System.out.println("Subscriber::process()");
        logger.info("Order Received. Fields "+ msg.getHostname() + msg.getIpAddr());
        latch.countDown();
       //return new String("ACKNOWLEDGE");
    }

    @RabbitListener(queues = "${smoc.rabbitmq.queue_2}")
    public void process_2(Message msg) {
        System.out.println("Subscriber::process_2()");
        logger.info("Order Received. Fields "+ msg.getHostname() + msg.getIpAddr());
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
