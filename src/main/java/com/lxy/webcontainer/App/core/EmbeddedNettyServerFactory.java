package com.lxy.webcontainer.App.core;

import org.springframework.boot.web.reactive.server.AbstractReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.server.reactive.HttpHandler;

public class EmbeddedNettyServerFactory extends AbstractReactiveWebServerFactory {

    @Override
    public WebServer getWebServer(HttpHandler httpHandler) {
        return null;
    }
}
