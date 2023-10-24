package net.emapp.webfluxsecurity.rabbitmq;

public record MessageModel(String message, String routingKey) {
}
