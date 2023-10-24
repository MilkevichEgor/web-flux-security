package net.emapp.webfluxsecurity.socket;

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
        System.out.println("Client connected: " + session.getId());

        Mono<Void> sendOnConnect = session.send(Mono.just(session.textMessage("Hello!")));

        Mono<Void> onDisconnect = session.receive()
                .then()
                .doOnTerminate(() -> {
                    System.out.println("Client disabled: " + session.getId());
                });

        var f = sink.asFlux()
                .map(s -> session.textMessage(s));

        return sendOnConnect.and(session.send(f)).then(onDisconnect);

    }

}
//    @Override
//    public Mono<Void> handle(WebSocketSession session) {
//
//        var f = sink.asFlux()
//                .map(s -> session.textMessage(s));
//
//        return session.send(f);
//    }

//    @Override
//    public Mono<Void> handle(WebSocketSession session) {
//
//        var f = session.receive()
//                .map(e -> e.getPayloadAsText())
//                .map(e -> new StringBuilder(e).reverse())
//                .map(e -> session.textMessage(e.toString()));
//
//        return session.send(f);
//    }

