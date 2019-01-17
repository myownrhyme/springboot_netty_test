package com.lxy.webcontainer.App.core;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;

public class EmbeddedNettyServerContainer   {


    public WebServer getWebServer(ServletContextInitializer... initializers) {
        return null;
    }
}
