package com.lxy.webcontainer.App.core;


import com.lxy.webcontainer.App.coreWithoutServlet.EmbeddedNettyServerFactory;
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
    @ConditionalOnClass({Bootstrap.class})
    @ConditionalOnMissingBean(value = EmbeddedNettyServerFactory.class, search = SearchStrategy.CURRENT)
    public static class EmbeddedNetty {
        @Bean
        public EmbeddedNettyServletFactory embeddedNettyFactory() {
            return new EmbeddedNettyServletFactory();
        }
    }
}
