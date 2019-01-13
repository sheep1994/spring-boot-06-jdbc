package com.talent.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-boot-01-helloworld
 * @author: Mr.Guo
 * @description: Servlet容器配置类
 * @create: 2019-01-13 16:25
 */
@Configuration
public class ServerConfig {

    /**
     * Java配置类方式配置servlet容器
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(8081);
        return factory;
    }
}
