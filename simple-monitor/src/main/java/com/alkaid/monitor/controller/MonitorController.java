package com.alkaid.monitor.controller;

import com.alkaid.monitor.component.Context;
import com.alkaid.monitor.component.MetricInfo;
import com.alkaid.monitor.entity.BandInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@RequestMapping("/monitor")
@RestController
public class MonitorController {

    @GetMapping(value="/metric")
    public String metric() throws Exception {
        /*
        MonitorService.cpu();
        MonitorService.memory();
        MonitorService.net();
        MonitorService.file();
        MonitorService.os();
        MonitorService.property();
        MonitorService.ethernet();
        MonitorService.who();
        String command = "netstat -an";
        String result = MonitorService.command(command);


        StringBuilder stringBuilder = new StringBuilder();
        double cpuPerc = MonitorService.cpuPerc();
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("cpu 使用率为" + df.format(cpuPerc));
        stringBuilder.append("CPU使用率: ").append(df.format(cpuPerc)).append("<br>");

        double memPerc = MonitorService.memoryPerc();
        System.out.println("内存 使用率为" + df.format(memPerc));
        stringBuilder.append("内存使用率: ").append(df.format(memPerc)).append("<br>");

        List<String> addressList = new ArrayList<>();
        addressList.add("192.168.1.103");
        List<BandInfo> bandInfoLit = MonitorService.bandWidth(addressList);
        DecimalFormat netDf = new DecimalFormat("#.00");
        bandInfoLit.forEach(it -> {
            stringBuilder.append("网卡").append(it.getName())
                    .append(" received ").append(df.format(it.getReceive())).append(it.getUnit())
                    .append(" sent ").append(df.format(it.getSend())).append(it.getUnit())
                    .append("<br>");
        });

        Commander commander = new Commander();
        String command = "netstat -an";
        int n = commander.comand(command).exec().filter("8080").count();
        stringBuilder.append(command).append(" filter: ").append("8080")
                .append(" count:").append(n);
        */
        StringBuilder stringBuilder = new StringBuilder();
        MetricInfo metricInfo = Context.getMetricInfo();
        List<Double> cpuPercentList = metricInfo.getCpuPercentList();
        DecimalFormat df = new DecimalFormat("0.00");
        stringBuilder.append("CPU使用率(%): ");
        stringBuilder.append("<br>");
        stringBuilder.append("-------------------------------------------------------------------");
        stringBuilder.append("<br>");
        cpuPercentList.forEach(it -> {
            stringBuilder.append(df.format(it)).append(", ");
        });
        stringBuilder.append("<br><br>");

        List<Double> memPercentList = metricInfo.getMemPercentList();
        stringBuilder.append("内存使用率(%): ");
        stringBuilder.append("<br>");
        stringBuilder.append("-------------------------------------------------------------------");
        stringBuilder.append("<br>");
        memPercentList.forEach(it -> {
            stringBuilder.append(df.format(it)).append(", ");
        });
        stringBuilder.append("<br><br>");

        List<List<BandInfo>> bandInfoList = metricInfo.getBandInfoList();
        DecimalFormat netDf = new DecimalFormat("0.00");
        stringBuilder.append("网络速率(MB/s): ");
        stringBuilder.append("<br>");
        stringBuilder.append("-------------------------------------------------------------------");
        stringBuilder.append("<br>");
        bandInfoList.forEach(it -> {
            it.forEach( bandInfo -> {
                stringBuilder.append(bandInfo.getName()).append("&nbsp;&nbsp;")
                        .append("  &nbsp;&nbsp;接收: ").append(netDf.format(bandInfo.getReceive()/60.00D)).append(bandInfo.getUnit())
                        .append("  &nbsp;&nbsp;发送: ").append(netDf.format(bandInfo.getSend()/60.00D)).append(bandInfo.getUnit())
                        .append("<br>");
            });
        });
        stringBuilder.append("<br>");
        Map<String, List<Integer>> connNumMap = metricInfo.getConnNumMap();
        stringBuilder.append("网络连接数(个)：");
        stringBuilder.append("<br>");
        stringBuilder.append("-------------------------------------------------------------------");
        stringBuilder.append("<br>");
        connNumMap.forEach((k,v) -> {
            stringBuilder.append(k).append(":&nbsp;&nbsp;");
            List<Integer> connNumList = v;
            connNumList.forEach(it -> {
                stringBuilder.append(it).append(", ");
            });
            stringBuilder.append("<br>");
        });

        stringBuilder.append("<br>");
        Map<String, List<Integer>> clientNumMap = metricInfo.getClientNumMap();
        stringBuilder.append("在线ip数(个)：");
        stringBuilder.append("<br>");
        stringBuilder.append("-------------------------------------------------------------------");
        stringBuilder.append("<br>");
        clientNumMap.forEach((k,v) -> {
            stringBuilder.append(k).append(":&nbsp;&nbsp;");
            List<Integer> clientNumList = v;
            clientNumList.forEach(it -> {
                stringBuilder.append(it).append(", ");
            });
            stringBuilder.append("<br>");
        });
        return stringBuilder.toString();
    }
}
