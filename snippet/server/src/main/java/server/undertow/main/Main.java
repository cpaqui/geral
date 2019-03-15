package server.undertow.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;
import io.undertow.util.Headers;
import io.undertow.util.StatusCodes;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.WebSocketProtocolHandshakeHandler;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.undertow.websockets.spi.WebSocketHttpExchange;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Undertow server = Undertow
                .builder()
                .addHttpListener(8080, "0.0.0.0", main.routingHandler())
                .addHttpListener(8081, "0.0.0.0", Handlers.path().addExactPath("socket", Main.webSocketConnectionCallback()))
                .addHttpListener(8083, "0.0.0.0", indexHtml())
                .build();

        server.start();
    }

    private static HttpHandler indexHtml() {
        ResourceHandler welcomeFile = Handlers
                .resource(new ClassPathResourceManager(Main.class.getClassLoader(), Main.class.getPackage()))
                .addWelcomeFiles("index.html");

        return Handlers.path().addExactPath("/", welcomeFile);
    }

    public static WebSocketProtocolHandshakeHandler webSocketConnectionCallback() {
        List<String> names = Arrays.asList("Rui", "Artur", "Constansa", "Helena", "José");
        Random random = new Random();

        WebSocketConnectionCallback webSocketConnectionCallback = new WebSocketConnectionCallback() {

            @Override
            public void onConnect(WebSocketHttpExchange exchange, WebSocketChannel channel) {
//        	String address = channel.getPeerAddress().toString();
//        	String name = names.get(random.nextInt(names.size()));
                channel.setAttribute("name", names.get(random.nextInt(names.size())));

                channel.getReceiveSetter().set(new AbstractReceiveListener() {

                    @Override
                    protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message) {
                        final String messageData = message.getData();
                        final String name = (String) channel.getAttribute("name");
                        for (WebSocketChannel session : channel.getPeerConnections()) {
                            if (!name.equals(session.getAttribute("name"))) {
                                WebSockets.sendText(String.format("%s say: %s", name, messageData), session, null);
                            }
                        }
                    }
                });
                channel.resumeReceives();
            }

        };

        return Handlers.websocket(webSocketConnectionCallback);
    }

    public RoutingHandler routingHandler() {
        HttpHandler post_ = this::post;
        return Handlers.routing()
                .post("post", post_)
                .get("get", this::get);
    }

    public void get(HttpServerExchange exchange) {
        exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Hello GET World!");
    }

    public void post(HttpServerExchange exchange) {
        exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, "text/plain");
        exchange.getRequestReceiver().receiveFullString(Main::readPayload, (exc, exception) -> {

        });

        Predicate<Integer> predicate = this::gtZero;
        predicate.and(this::ltTen);
        predicate.test(0);

        Function<Integer, Double> f = this::map;

        Stream integerStream = Arrays.asList(1, 2, 3).stream().filter(predicate).map(f);
    }
    
    private boolean gtZero (Integer i) {
        return false;
    }

    private boolean ltTen (Integer i) {
        return false;
    }

    private Double map (Integer i) {
        return 0D;
    }

    public static void readPayload(HttpServerExchange exchange, String body) {
        exchange.getResponseSender().send(String.format("Hello POST World! (payload: %s)", body));
    }

    public static void throwsException(HttpServerExchange exchange, IOException exception) {
        exchange.getResponseHeaders().add(Headers.STATUS, StatusCodes.BAD_REQUEST);
        exchange.getResponseSender().send(String.format("Hello POST World! (payload: %s)", exception.getMessage()));
    }

}
