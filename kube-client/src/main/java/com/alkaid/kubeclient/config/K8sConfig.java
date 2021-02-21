package com.alkaid.kubeclient.config;

import com.alkaid.kubeclient.util.K8sUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;


@Configuration
@EnableConfigurationProperties(K8sConfig.K8sProperties.class)
public class K8sConfig {

    /**
     * K8s properties
     */
    @Autowired
    private K8sProperties k8sProperties;

    @Data
    @ConfigurationProperties("kubernetes")
    public static class K8sProperties {
        @Value("url")
        private String url;
        // 注意用 Resource
        @Value("client-crt")
        private Resource clientCrt;
        @Value("client-key")
        private Resource clientKey;
        @Value("ca-crt")
        private Resource caCrt;
    }

    @Bean
    public K8sUtils k8sUtils() {
        if (k8sProperties == null) {
            return null;
        }
        final String url = k8sProperties.getUrl();
        if(StringUtils.isEmpty(url)){
            return null;
        }
        return new K8sUtils(k8sProperties);
    }
}

