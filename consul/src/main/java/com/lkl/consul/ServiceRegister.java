package com.lkl.consul;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 服务注册
 * Created by liaokailin on 16/6/2.
 */
public class ServiceRegister {

    @Test
    public void registerServiceForConsul() throws Exception {
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromParts("127.0.0.1", 8500)).build();
        AgentClient agentClient = consul.agentClient();
        String serviceId = UUID.randomUUID().toString();
        String serviceName = "java-consul-service";
        String serviceTag = "test";
        long ttl = 3l;
        agentClient.register(9090, ttl, serviceName, serviceId, serviceTag);
        agentClient.pass(serviceId);
        System.out.println("succ.");
        TimeUnit.MINUTES.sleep(2);
    }
}
