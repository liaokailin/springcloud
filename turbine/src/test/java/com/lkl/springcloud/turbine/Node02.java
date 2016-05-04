package com.lkl.springcloud.turbine;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaokailin on 16/5/4.
 */
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
public class Node02 {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Node02.class).properties(
                "spring.config.name:node02").run(args);
    }

    @Autowired
    private HelloService service;

    @RequestMapping("/")
    public String hello() {
        return this.service.hello();
    }


    @Component
 public static class HelloService {

        @HystrixCommand(fallbackMethod="fallback")
        public String hello() {
            return "Hello World";
        }
        public String fallback() {
            return "Fallback";
        }
    }
}
