package com.alkaid.monitor.task;

import static com.alkaid.monitor.configure.MetricConfig.CONNECT_CONFIG_ADDRESS;
import static com.alkaid.monitor.configure.MetricConfig.CONNECT_CONFIG_PORT;
import static com.alkaid.monitor.configure.MetricConfig.CONNECT_CONFIG_STATUS;
import static com.alkaid.monitor.configure.MetricConfig.NET_ADDRESS;

import com.alkaid.monitor.component.Commander;
import com.alkaid.monitor.component.Context;
import com.alkaid.monitor.component.MetricInfo;
import com.alkaid.monitor.configure.MetricConfig;
import com.alkaid.monitor.entity.BandInfo;
import com.alkaid.monitor.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MonitorTask {

    @Value("${metric.net.address}")
    private String netAddress;

    @Autowired
    private MetricConfig metricConfig;

    @Scheduled(fixedRate = 60*1000)
    public void metric() throws Exception {
        System.out.println("监控task运行...... " + System.currentTimeMillis());
        MetricInfo metricInfo = Context.getMetricInfo();
        metricInfo.setMaxSize(metricConfig.getCacheSize());

        Double cpuPercent = MonitorService.cpuPerc();
        metricInfo.addCpuPercent(cpuPercent);

        Double memPercent = MonitorService.memoryPerc();
        metricInfo.addMemPercent(memPercent);

        List<String> addressList = new ArrayList<>();
        addressList.add(metricConfig.getNet().get(NET_ADDRESS));
        List<BandInfo> bandInfoList = MonitorService.bandWidth(addressList);
        metricInfo.addBandInfo(bandInfoList);

        List<Map<String, String>> connectionList = metricConfig.getConnectionConfigList();
        connectionList.forEach(it -> {
            Commander commander = new Commander();
            String conn = it.get(CONNECT_CONFIG_ADDRESS)+":"+it.get(CONNECT_CONFIG_PORT);
            String command = "netstat -an";
            int connNum = commander.comand(command).exec().filter(conn).filter(it.get(CONNECT_CONFIG_STATUS)).count();
            metricInfo.addConnNum(conn, connNum);
        });

        connectionList.forEach(it -> {
            Commander commander = new Commander();
            String conn = it.get(CONNECT_CONFIG_ADDRESS)+":"+it.get(CONNECT_CONFIG_PORT);
            String command = "netstat -an";
            int connNum = commander.comand(command)
                    .exec()
                    .filter(conn)
                    .filter(it.get(CONNECT_CONFIG_STATUS))
                    .selectColumn(" ", 2)
                    .selectColumn(":", 0)
                    .groupBy()
                    .count();
            metricInfo.addClientNum(conn, connNum);
        });
    }

}
