package main;

import itu.celikelni.ipc.Message;
import itu.celikelni.ipc.Publisher;
import itu.celikelni.ipc.Subscriber;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

/*
*Reference: http://candidjava.com/tutorial/spring-boot-rabbitmq-example-using-maven/
*/

@SpringBootApplication
@ComponentScan(basePackages = {"itu.celikelni.ipc"})
public class Application implements CommandLineRunner {

    @Autowired
    private Publisher publisher;

    @Autowired
    private Subscriber subscriber;

    @Override
    public void run(String... args) throws Exception {
        Message msg = new Message();
        msg.setHostname("niyazi");
        msg.setIpAddr("192.168.17.17");
        publisher.send(msg);

        msg.setHostname("niyazi-2");
        msg.setIpAddr("141.29.17.41");
        publisher.send_2(msg);
        //System.out.println("REPLY : " + reply);
        //subscriber.getLatch().await(10, TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
