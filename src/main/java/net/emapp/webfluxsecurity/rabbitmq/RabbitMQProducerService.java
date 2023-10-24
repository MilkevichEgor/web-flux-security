package net.emapp.webfluxsecurity.rabbitmq;

public interface RabbitMQProducerService {

    void sendMessage(String message, String routingKey);
}
