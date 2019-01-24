package com.lxy.webcontainer.App.core;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerException;

public class NettyServletContainer implements WebServer {

    @Override
    public void start() throws WebServerException {

    }

    @Override
    public void stop() throws WebServerException {

    }

    @Override
    public int getPort() {
        return 0;
    }
}
