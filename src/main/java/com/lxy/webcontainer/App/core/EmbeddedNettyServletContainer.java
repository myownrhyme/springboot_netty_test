package com.lxy.webcontainer.App.core;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class EmbeddedNettyServletContainer extends AbstractServletWebServerFactory implements ResourceLoaderAware {

    @Override
    public WebServer getWebServer(ServletContextInitializer... initializers) {
        ServletContext context = null;
        for(ServletContextInitializer initializer : initializers){
            try{
                initializer.onStartup(context);
            }catch (ServletException e){
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

    }
}
