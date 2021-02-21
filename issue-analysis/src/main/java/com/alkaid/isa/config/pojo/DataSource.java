package com.alkaid.isa.config.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DataSource {
    @TableId("id")
    private long id;
    private String name;
    private String databaseType;
    private String host;
    private String port;
    private String database;
    private String user;
    private String password;
}
