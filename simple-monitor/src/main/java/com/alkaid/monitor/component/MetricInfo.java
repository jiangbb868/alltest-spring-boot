package com.alkaid.monitor.component;

import com.alkaid.monitor.entity.BandInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricInfo {

    private int maxSize = 12;
    private List<Double> cpuPercentList;
    private List<Double> memPercentList;
    private List<List<BandInfo>> bandInfoList;
    private Map<String, List<Integer>> connNumMap;
    private Map<String, List<Integer>> clientNumMap;



    public MetricInfo() {
        cpuPercentList = new ArrayList<>();
        memPercentList = new ArrayList<>();
        bandInfoList = new ArrayList<>();
        connNumMap = new HashMap<>();
        clientNumMap = new HashMap<>();
    }

    public void addCpuPercent(Double cpuPercent) {
        cpuPercentList.add(cpuPercent);
        checkSize(cpuPercentList);
    }

    public void addMemPercent(Double memPercent) {
        memPercentList.add(memPercent);
        checkSize(memPercentList);
    }

    public void addBandInfo(List<BandInfo> bandInfos) {
        bandInfoList.add(bandInfos);
        checkSize(bandInfoList);
    }

    public void addConnNum(String conn, Integer connNum) {
        List<Integer> connNumList;
        if (connNumMap.containsKey(conn)) {
            connNumList = connNumMap.get(conn);
        } else {
            connNumList = new ArrayList<>();
        }
        connNumList.add(connNum);
        checkSize(connNumList);
        connNumMap.put(conn, connNumList);
    }

    public void addClientNum(String conn, Integer clientNum) {
        List<Integer> clientNumList;
        if (clientNumMap.containsKey(conn)) {
            clientNumList = clientNumMap.get(conn);
        } else {
            clientNumList = new ArrayList<>();
        }
        clientNumList.add(clientNum);
        checkSize(clientNumList);
        clientNumMap.put(conn, clientNumList);
    }

    private void checkSize(List list) {
        if (list.size() > maxSize) {
            list.remove(0);
        }
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Double> getCpuPercentList() {
        return cpuPercentList;
    }

    public List<Double> getMemPercentList() {
        return memPercentList;
    }

    public List<List<BandInfo>> getBandInfoList() {
        return bandInfoList;
    }

    public Map<String, List<Integer>> getConnNumMap() {
        return connNumMap;
    }

    public Map<String, List<Integer>> getClientNumMap() {
        return clientNumMap;
    }
}
