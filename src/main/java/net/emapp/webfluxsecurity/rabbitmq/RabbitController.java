package net.emapp.webfluxsecurity.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class RabbitController {

    private final RabbitMQProducerService rabbitMQProducerService;

    @Autowired
    public RabbitController(RabbitMQProducerService rabbitMQProducerService) {
        this.rabbitMQProducerService = rabbitMQProducerService;
    }

    @PostMapping("/send")
    public Mono<String> sendMessageToRabbit(@RequestBody MessageModel messageModel) {
        rabbitMQProducerService.sendMessage(messageModel.message(), messageModel.routingKey());
        System.out.println("Message sent");

        return Mono.just("OK");
    }

}
