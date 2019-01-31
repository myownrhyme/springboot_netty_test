package com.lxy.webcontainer.App.core;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.net.InetSocketAddress;

public class EmbeddedNettyServletFactory extends AbstractServletWebServerFactory implements ResourceLoaderAware {

    @Override
    public WebServer getWebServer(ServletContextInitializer... initializers) {

        InetSocketAddress address = new InetSocketAddress(9999);
        ServletContext context = new NettyServletContext(getContextPath());
        for(ServletContextInitializer initializer : initializers){
            try{
                initializer.onStartup(context);
            }catch (ServletException e){
                throw new RuntimeException(e);
            }
        }

        return new NettyServletContainer(address,context);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

    }
}
