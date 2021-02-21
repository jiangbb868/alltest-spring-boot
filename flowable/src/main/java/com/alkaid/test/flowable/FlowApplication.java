package com.alkaid.test.flowable;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class FlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowApplication.class, args);

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                deployStringProcess(repositoryService, runtimeService, taskService);
                System.out.println("Number of process definitions : "
                        + repositoryService.createProcessDefinitionQuery().count());
                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
                runtimeService.startProcessInstanceByKey("oneTaskProcess");
                System.out.println("Number of tasks after process start: "
                        + taskService.createTaskQuery().count());

            }

            private void deployStringProcess(final RepositoryService repositoryService,
                                             final RuntimeService runtimeService,
                                             final TaskService taskService) {
                DeploymentBuilder builder = repositoryService.createDeployment();
                String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><bpmn2:definitions xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xsi:schemaLocation=\"http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd\" id=\"sample-diagram\" targetNamespace=\"http://bpmn.io/schema/bpmn\"><bpmn2:process id=\"Process_1\" isExecutable=\"true\"><bpmn2:startEvent id=\"StartEvent_1\"/></bpmn2:process><bpmndi:BPMNDiagram id=\"BPMNDiagram_1\"><bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"Process_1\"><bpmndi:BPMNShape id=\"_BPMNShape_StartEvent_2\" bpmnElement=\"StartEvent_1\"><dc:Bounds height=\"36.0\" width=\"36.0\" x=\"412.0\" y=\"240.0\"/></bpmndi:BPMNShape></bpmndi:BPMNPlane></bpmndi:BPMNDiagram></bpmn2:definitions>";
                String xml1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<definitions\n" +
                        "        xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"\n" +
                        "        xmlns:flowable=\"http://flowable.org/bpmn\"\n" +
                        "        targetNamespace=\"Examples\">\n" +
                        "\n" +
                        "    <process id=\"stringProcess\" name=\"The One Task Process\">\n" +
                        "        <startEvent id=\"theStart\" />\n" +
                        "        <sequenceFlow id=\"flow1\" sourceRef=\"theStart\" targetRef=\"firstStep\" />\n" +
                        "        <userTask id=\"firstStep\" name=\"first task\" flowable:assignee=\"jiangb\" />\n" +
                        "        <sequenceFlow id=\"flow2\" sourceRef=\"firstStep\" targetRef=\"secondStep\" />\n" +
                        "        <userTask id=\"secondStep\" name=\"second task\" flowable:assignee=\"jiangb\" />\n" +
                        "        <sequenceFlow id=\"flow3\" sourceRef=\"secondStep\" targetRef=\"thirdStep\" />\n" +
                        "        <serviceTask id=\"thirdStep\" name=\"third task\" flowable:class=\"com.alkaid.test.flowable.task.service.JavaService\" >\n" +
                        "        <extensionElements>\n" +
                        "        <flowable:field name=\"path\" stringValue=\"https://www.baidu.com/\" />\n" +
                        "        <flowable:field name=\"parameters\" stringValue=\"jiangb\" />\n" +
                        "        </extensionElements>" +
                        "        </serviceTask> " +
                        "        <sequenceFlow id=\"flow4\" sourceRef=\"thirdStep\" targetRef=\"theEnd\" />\n" +
                        "        <endEvent id=\"theEnd\" />\n" +
                        "    </process>\n" +
                        "\n" +
                        "</definitions>";
                builder.addString("string-process.bpmn20.xml", xml1).deploy();

                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("stringProcess");
                List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
                System.out.println("task count " + taskList.size());
                for (Task task : taskList) {
                    System.out.println("task: " + task.getId() + " " + task.getName());
                    taskService.complete(task.getId());
                }
                taskList = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
                System.out.println("task count " + taskList.size());
                for (Task task : taskList) {
                    System.out.println("task: " + task.getId() + " " + task.getName());
                    taskService.complete(task.getId());
                }
            }
        };
    }
}
