package com.alkaid.monitor.component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Commander {

    private String command;
    private List<String> resultList;
    private List<String> filterList;

    public Commander() {
        resultList = new ArrayList<>();
    }

    public Commander comand(String command) {
        this.command = command;
        return this;
    }

    public Commander exec() {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(command);
            br = new BufferedReader(
                    new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));
            String line = null;
            while ((line = br.readLine()) != null) {
                resultList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    public Commander exec(String command) {
        this.command = command;
        return exec();
    }

    public Commander filter(String filter) {
        List<String> list = null;
        if (filterList != null) {
            list = filterList;
        } else {
            list = resultList;
        }
        if (list != null && list.size() > 0) {
            filterList = list.stream().filter(it -> it.indexOf(filter) != -1).collect(
                    Collectors.toList());
        }
        return this;
    }

    public Commander selectColumn(String string, int index) {
        if (string == null || string.length() == 0) {
            return this;
        }
        if (index < 0) {
            return this;
        }
        List<String> list = null;
        if (filterList != null) {
            list = filterList;
        } else {
            list = resultList;
        }
        if (list != null && list.size() > 0) {
            List columnList = new ArrayList();
            list.forEach(it -> {
                String[] elements = it.split(string);
                Object[] objects = Arrays.stream(elements)
                        .filter(t -> !t.equals(string) && t.length() > 0)
                        .toArray();
                if (index < objects.length) {
                    columnList.add(objects[index]);
                }
            });
            filterList = columnList;
        }
        return this;
    }

    public Commander groupBy() {
        List<String> list = null;
        if (filterList != null) {
            list = filterList;
        } else {
            list = resultList;
        }
        if (list != null && list.size() > 0) {
            Set set = new HashSet(list);
            filterList = new ArrayList<>(set);
        }
        return this;
    }

    public int count() {
        if (filterList != null && filterList.size() > 0) {
            return filterList.size();
        }
        return 0;
    }

    public String resultString() {
        if (resultList != null && resultList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            resultList.forEach( it -> {
                stringBuilder.append(it);
            });
            return stringBuilder.toString();
        }
        return null;
    }

    public List<String> resultList() {
        return resultList;
    }
}
