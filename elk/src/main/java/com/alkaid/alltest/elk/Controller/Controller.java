package com.alkaid.alltest.elk.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/{name}")
    public String hi(@PathVariable(value = "name") String name) {
        logger.info( "name = {}" , name );
        return "hi , " + name;
    }
}
