package com.alkaid.kubeclient.controller;

import com.alkaid.kubeclient.util.K8sUtils;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/kube")
@RestController
public class KubeController {

    @Autowired
    private ApiClient kubeApiClient;
    @Autowired
    private K8sUtils k8sUtils;

    @GetMapping(value="/pod/list")
    public List<String> podList() throws Exception {
        CoreV1Api api = new CoreV1Api();
        api.setApiClient(kubeApiClient);
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            System.out.println(item.getMetadata().getName());
        }
        return null;
    }

    @GetMapping(value="/service/list")
    @ResponseBody
    public ServiceList serviceList() throws Exception {
        KubernetesClient client = k8sUtils.getClient();
        ServiceList services = client.services().inNamespace("base-service").list();
        return services;
    }


    @GetMapping(value="/pods/list")
    @ResponseBody
    public PodList podsList() throws Exception {
        KubernetesClient client = k8sUtils.getClient();
        PodList pods = client.pods().inNamespace("base-service").list();
        return pods;
    }
}
