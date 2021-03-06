

package com.lkl.springcloud.ribbon;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
public class SimpleApplication {
	
	@RequestMapping("/")
	public String hello() {
		return "Hello";
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SimpleApplication.class).properties(
				"spring.config.name:simple").run(args);
	}	
}
