package com.lkl.consul;

import com.google.common.base.Optional;
import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.kv.Value;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定义拉取key value
 * Created by liaokailin on 16/6/14.
 */
public class ScheduleKV implements Runnable {

    public static ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
    public static Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromParts("127.0.0.1", 8500)).build();

    @Test
    public void testSchedulKV() throws  Exception{
        es.scheduleAtFixedRate(new ScheduleKV(),2,5, TimeUnit.SECONDS);
        TimeUnit.MINUTES.sleep(10);
    }


    @Override
    public void run() {
        KeyValueClient keyValueClient =  consul.keyValueClient();
        Optional<Value> optValue =  keyValueClient.getValue("com/lkl/consul/kvs");
        if (optValue.isPresent()) {
            Value value = optValue.get();
            long flag = value.getFlags();
            System.out.println("flag:" + flag);
            String propString = new String(Base64.decodeBase64(value.getValue().get()));
            System.out.println(propString);
        }
    }
}
