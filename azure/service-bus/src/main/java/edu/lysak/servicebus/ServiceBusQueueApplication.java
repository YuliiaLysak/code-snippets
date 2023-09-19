package edu.lysak.servicebus;

import com.azure.spring.messaging.implementation.annotation.EnableAzureMessaging;
import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@EnableAzureMessaging
public class ServiceBusQueueApplication {

    private static final String QUEUE_NAME = "reserved-order-items";


    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServiceBusQueueApplication.class);
        ServiceBusTemplate serviceBusTemplate = applicationContext.getBean(ServiceBusTemplate.class);
        for (int i = 0; i < 10; i++) {
            String order = String.format("{\"id\": \"uuid%s\"}", i);
            serviceBusTemplate.sendAsync(QUEUE_NAME, MessageBuilder.withPayload(order).build()).subscribe();
            System.out.println("Sent message to the queue, i=" + i);
            Thread.sleep(5000);
        }
    }
}
