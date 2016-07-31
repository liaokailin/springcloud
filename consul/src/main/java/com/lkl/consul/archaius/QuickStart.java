package com.lkl.consul.archaius;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.orbitz.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by liaokailin on 16/6/14.
 */
public class QuickStart {

    static{
        System.setProperty("archaius.configurationSource.defaultFileName", "application.properties");  //default application.properties
        System.setProperty("archaius.fixedDelayPollingScheduler.initialDelayMills", "1");  //default 30000
        System.setProperty("archaius.fixedDelayPollingScheduler.delayMills", "1"); //default 60000
    }

    public static final String DEFAULT_VALUE = StringUtils.EMPTY;
    public static DynamicStringProperty prop = DynamicPropertyFactory.getInstance().getStringProperty("name", DEFAULT_VALUE);

    public static void main(String[] args) throws Exception {



        while (true) {
            System.out.println(prop.get());
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
