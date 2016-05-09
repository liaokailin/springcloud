package com.lkl.springcloud.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;

/**
 * Created by liaokailin on 16/5/6.
 */
//@Configuration
public class MyDefaultRibbonConfig {
    @Bean
    public IRule ribbonRule() {
        return new BestAvailableRule();
    }

    @Bean
    public IPing ribbonPing() {
        return new PingUrl();
    }

    @Bean
    public ServerList<Server> ribbonServerList(IClientConfig config) {
        return new MyDefaultRibbonConfig.BazServiceList(config);
    }

    @Bean
    public ServerListSubsetFilter serverListFilter() {
        ServerListSubsetFilter filter = new ServerListSubsetFilter();
        return filter;
    }

    public static class BazServiceList extends ConfigurationBasedServerList {
        public BazServiceList(IClientConfig config) {
            super.initWithNiwsConfig(config);
        }
    }
}
