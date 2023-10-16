package net.proselyte.webfluxsecurity.rabbitmq;

public record MessageModel(String message, String routingKey) {
}
