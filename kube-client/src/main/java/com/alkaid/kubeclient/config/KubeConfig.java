package com.alkaid.kubeclient.config;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.SSLUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.KeyManager;
import java.io.*;
import java.nio.file.Files;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;


@Data
@Configuration
public class KubeConfig {

    @Value("${kubernetes.api-url}")
    private String apiUrl;
    @Value("${kubernetes.ssl.path}")
    private String sslPath;
    @Value("${kubernetes.ssl.client-cert}")
    private String cert;
    @Value("${kubernetes.ssl.client-key}")
    private String key;

    @Bean
    public ApiClient apiClient() throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, InvalidKeySpecException {
        ApiClient client = new ApiClient();
        client.setBasePath(apiUrl);

//        String certPath = Resources.getResource(sslPath+cert).getPath();
//        String keyPath = Resources.getResource(sslPath+key).getPath();
        File certFile = ResourceUtils.getFile("classpath:"+sslPath+cert);
        File keyFile = ResourceUtils.getFile("classpath:"+sslPath+key);
        final byte[] certBytes = Files.readAllBytes(certFile.toPath());
        final byte[] keyBytes = Files.readAllBytes(keyFile.toPath());

        String dataString = new String(keyBytes);
        String algo = "";
        if (dataString.indexOf("BEGIN EC PRIVATE KEY") != -1) {
            algo = "EC";
        }
        if (dataString.indexOf("BEGIN RSA PRIVATE KEY") != -1) {
            algo = "RSA";
        }
        final KeyManager[] keyManagers = SSLUtils.keyManagers(certBytes, keyBytes, algo, "", null, null);
        client.setKeyManagers(keyManagers);
        io.kubernetes.client.openapi.Configuration.setDefaultApiClient(client);
        return client;
    }
}
