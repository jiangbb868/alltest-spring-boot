package com.alkaid.monitor.configure;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@Configuration
//@PropertySource("classpath:application.yaml")
//@ConfigurationProperties(prefix = "metric")

@Component
@ConfigurationProperties(prefix = "metric")
public class MetricConfig {

    private String interval;
    private int cacheSize;
    private Map<String, String> net;
    private List<Map<String, String>> connectionConfigList;

    public final static String CONNECT_CONFIG_ADDRESS = "address";
    public final static String CONNECT_CONFIG_PORT = "port";
    public final static String CONNECT_CONFIG_STATUS = "status";
    public final static String NET_ADDRESS = "address";

    public List<Map<String, String>> getConnectionConfigList() {
        return connectionConfigList;
    }

    public void setConnectionConfigList(
            List<Map<String, String>> connectionConfigList) {
        this.connectionConfigList = connectionConfigList;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Map<String, String> getNet() {
        return net;
    }

    public void setNet(Map<String, String> net) {
        this.net = net;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
