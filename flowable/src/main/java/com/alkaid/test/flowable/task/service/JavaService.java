package com.alkaid.test.flowable.task.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class JavaService implements JavaDelegate {

    @Autowired
    private RestTemplate restTemplate;

    private Expression path;
    private Expression parameters;
    @Override
    public void execute(DelegateExecution execution) {
        String pathString = path.getValue(execution).toString();
        log.info("enter execute url: {}", pathString);
        if (parameters.getValue(execution) != null) {
            String parametersString = parameters.getValue(execution).toString();
            log.info("enter execute parameters: {}", pathString, parametersString);
        }
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        String response = restTemplate.getForObject(pathString, String.class);
        log.info("response is {}", response);
    }
}
