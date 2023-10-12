package net.proselyte.webfluxsecurity.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;


@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private final Sinks.Many<String> sink;

    public MyWebSocketHandler(Sinks.Many<String> sink) {
        this.sink = sink;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        var f = sink.asFlux()
                .map(s -> session.textMessage(s));



        return session.send(f);
    }
}
