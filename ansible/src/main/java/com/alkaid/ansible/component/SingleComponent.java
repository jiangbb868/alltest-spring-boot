package com.alkaid.ansible.component;

import lombok.Data;

import java.io.IOException;
import java.util.Map;

@Data
public class SingleComponent implements IComponent{

    private String command;

    @Override
    public String exec() throws IOException {
        Process p = Runtime.getRuntime().exec(command);
        return null;
    }

    @Override
    public String exec(Map<String, Object> param) throws IOException {
        Runtime.getRuntime().exec(command, (String[])param.values().stream().map(Object::toString).toArray());
        return null;
    }

}
