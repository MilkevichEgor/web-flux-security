package net.proselyte.webfluxsecurity.rabbitmq;

public interface RabbitMQProducerService {

    void sendMessage(String message, String routingKey);
}
