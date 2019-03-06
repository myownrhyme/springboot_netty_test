package com.lxy.webcontainer.App.coreWithoutServlet;

import com.sun.net.httpserver.HttpServer;

import org.springframework.boot.web.reactive.server.AbstractReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;

public class EmbeddedNettyServerFactory extends AbstractReactiveWebServerFactory {

    @Override
    public WebServer getWebServer(HttpHandler httpHandler) {
        HttpServer server = createhttpServer();
        ReactorHttpHandlerAdapter handlerAdapter = new ReactorHttpHandlerAdapter(
                httpHandler);
        NettyWebServer nettyWebServer = new NettyWebServer(server, handlerAdapter);
        return nettyWebServer;

    }

    private HttpServer createhttpServer() {
        return null;
    }
}
