package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@ImportResource(locations={"classpath:transaction.xml"})
@EnableTransactionManagement
@SpringBootApplication
@EnableCaching
public class ApplicationStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}