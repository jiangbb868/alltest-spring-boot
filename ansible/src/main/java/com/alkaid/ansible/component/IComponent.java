package com.alkaid.ansible.component;

import java.io.IOException;
import java.util.Map;

public interface IComponent {

    String exec() throws IOException;

    String exec(Map<String, Object> param) throws IOException;


}
