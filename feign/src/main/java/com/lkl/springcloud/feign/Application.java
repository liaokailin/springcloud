package com.lkl.springcloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liaokailin on 16/5/11.
 */
@Configuration
@EnableAutoConfiguration
@EnableFeignClients
@RestController
public class Application {

    @Autowired
    GitHub gitHub;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @FeignClient(url = "https://api.github.com")
    interface GitHub {
        @RequestMapping(value = "/repos/{owner}/{repo}/contributors", method = RequestMethod.GET)
      ResponseEntity<List<Contributor>> contributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
    }

    @RequestMapping("/")
    public void test(){
        ResponseEntity< List<Contributor>>  responseEntity  =   gitHub.contributors("netflix", "feign");
        List<Contributor> contributors = responseEntity.getBody();
         for (Contributor contributor : contributors) {
         System.out.println(contributor.login + " (" + contributor.contributions + ")");
         }
    }


    static class Contributor {
        String login;
        int contributions;
    }
}
