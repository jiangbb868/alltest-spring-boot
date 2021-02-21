package com.alkaid.kubeclient;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KubeClientApplication {
    public static void main(String[] args) throws Exception {

//        CoreV1Api api = new CoreV1Api();
//        api.setApiClient(client);
//        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
//        for (V1Pod item : list.getItems()) {
//            System.out.println(item.getMetadata().getName());
//        }
        SpringApplication.run(KubeClientApplication.class, args);
    }
}
