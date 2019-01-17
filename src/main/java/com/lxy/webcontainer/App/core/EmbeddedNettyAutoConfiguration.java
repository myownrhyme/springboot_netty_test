package com.lxy.webcontainer.App.core;


import io.netty.bootstrap.Bootstrap;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnWebApplication
public class EmbeddedNettyAutoConfiguration {

    @Configuration
    @ConditionalOnClass({Bootstrap.class}) // Netty的Bootstrap类必须在classloader中存在，才能启动Netty容器
    @ConditionalOnMissingBean(value = EmbeddedNettyServerContainer.class, search = SearchStrategy.CURRENT)
    public static class EmbeddedNetty {
        //上述条件注解成立的话就会构造EmbeddedNettyFactory这个EmbeddedServletContainerFactory
        @Bean
        public EmbeddedNettyServletContainer embeddedNettyFactory() {
            return new EmbeddedNettyServletContainer();
        }
    }
}
