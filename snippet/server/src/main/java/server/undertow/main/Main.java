package server.undertow.main;

import java.io.IOException;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Headers;
import io.undertow.util.StatusCodes;

public class Main {

    public static void main(String[] args) {
	Undertow server = Undertow
		.builder()
		.addHttpListener(8080, "0.0.0.0", routingHandler())
		.build();

	server.start();
    }
    
    public static RoutingHandler routingHandler () {
	return Handlers.routing()
		       .post("post", Main::post)
		       .get("get", Main::get);
    }

    public static void get(HttpServerExchange exchange) {	
	exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, "text/plain");
	exchange.getResponseSender().send("Hello GET World!");
    }

    public static void post(HttpServerExchange exchange) {
	exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, "text/plain");		
	exchange.getRequestReceiver().receiveFullString(Main::readPayload , Main::throwsException);
    }
    
    public static void readPayload (HttpServerExchange exchange, String body) {
	exchange.getResponseSender().send(String.format("Hello POST World! (payload: %s)", body));	
    }
    
    public static void throwsException (HttpServerExchange exchange, IOException exception) {
	exchange.getResponseHeaders().add(Headers.STATUS, StatusCodes.BAD_REQUEST);
	exchange.getResponseSender().send(String.format("Hello POST World! (payload: %s)", exception.getMessage()));
    }
    
}
