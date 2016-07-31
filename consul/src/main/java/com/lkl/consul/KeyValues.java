package com.lkl.consul;

import com.google.common.base.Optional;
import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.kv.Value;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.List;

/**
 * key-value操作
 * Created by liaokailin on 16/6/3.
 */
public class KeyValues {

    /**
     * 新增一个key
     */
    @Test
    public void pubKey() {
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromParts("127.0.0.1", 8500)).build();
        KeyValueClient keyValueClient = consul.keyValueClient();
        keyValueClient.putValue("/web/key3","test");
    }

    /**
     * 按key获取值
     */
    @Test
    public void getValueByKey(){
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromParts("127.0.0.1", 8500)).build();
        KeyValueClient keyValueClient = consul.keyValueClient();
        Optional<String> result = keyValueClient.getValueAsString("/web/key3");
        System.out.println(result.get());
    }

    /**
     * 获取values
     */
    @Test
    public void getValues(){
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromParts("127.0.0.1", 8500)).build();
        KeyValueClient keyValueClient = consul.keyValueClient();

        List<Value> values =  keyValueClient.getValues("/web");
        for(Value value : values){
            System.out.println(value.getKey()+","+ new String(Base64.decodeBase64(value.getValue().get())) );
        }
    }



}
