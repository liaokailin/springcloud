package com.lkl.springcloud.ribbon;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaServerApplication.class).properties(
				"spring.config.name:eureka", "logging.level.com.netflix.discovery:OFF")
				.run(args);
	}

}
