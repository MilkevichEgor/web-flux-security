WebSocketClient client = new ReactorNettyWebSocketClient();
URI uri = URI.create("ws://localhost:8083/uppercase");

Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1));
client.execute(uri, webSocketSession ->
    // send msg
    webSocketSession.send(
            longFlux.map(i -> webSocketSession.textMessage("vinsguru" + i))
    ).and(
            // receive message
            webSocketSession.receive()
                    .map(WebSocketMessage::getPayloadAsText)
                    .doOnNext(System.out::println)
    ).then()
).block(Duration.ofSeconds(5));