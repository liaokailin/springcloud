package com.lkl.consul;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import org.junit.Test;

import java.net.URI;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 健康检查
 * Created by liaokailin on 16/6/2.
 */
public class HealthCheck {

    @Test
    public void doHealthCheck() throws Exception {
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromParts("127.0.0.1", 8500)).build();
        AgentClient agentClient = consul.agentClient();
        String serviceName = "health-check-service";
        String serviceTag = "test";
        agentClient.register(9090, HostAndPort.fromParts("127.0.0.1", 8080), 3l, serviceName, "service-id", serviceTag);
        TimeUnit.MINUTES.sleep(2);
    }
}
