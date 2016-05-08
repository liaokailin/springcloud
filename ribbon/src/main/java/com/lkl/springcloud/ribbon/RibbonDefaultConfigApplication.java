package com.lkl.springcloud.ribbon;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liaokailin on 16/5/6.
 */
@SpringBootApplication
@RibbonClients(defaultConfiguration=MyDefaultRibbonConfig.class)  //设置的配置，就不会去加载默认的配置信息
@Configuration
@RestController
public class RibbonDefaultConfigApplication {
    @Autowired
    private SpringClientFactory clientFactory;

    /**
     * Throws exception if the SpringClientFactory doesn't return a balancer with a server list
     * of the expected type.
     *
     */
    @RequestMapping("/")
    public void test() throws Exception {
        ZoneAwareLoadBalancer<Server> lb = (ZoneAwareLoadBalancer<Server>)clientFactory.getLoadBalancer("client");
        ServerList<Server> serverList= lb.getServerListImpl();

        if( !(serverList instanceof MyDefaultRibbonConfig.BazServiceList) ) {
            throw new Exception("wrong server list type");
        }{
         List<Server> serverList02 = serverList.getInitialListOfServers();
            if(!CollectionUtils.isEmpty(serverList02)){
                for(Server s : serverList02){
                    System.out.println(s.getHost()+","+s.getPort());
                }
            }

            throw new Exception("wrong server list type");
        }
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder(RibbonDefaultConfigApplication.class).build();
        app.run(args);
    }

}
