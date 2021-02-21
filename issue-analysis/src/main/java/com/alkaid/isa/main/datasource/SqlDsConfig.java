package com.alkaid.isa.main.datasource;

import com.alkaid.isa.config.pojo.DataSource;
public class SqlDsConfig {
    private String type;
    private String host;
    private String port;
    private String database;
    private String user;
    private String password;

    public SqlDsConfig(DataSource dataSource) {
        this.type = dataSource.getDatabaseType();
        this.host = dataSource.getHost();
        this.port = dataSource.getPort();
        this.database = dataSource.getDatabase();
        this.user = dataSource.getUser();
        this.password = dataSource.getPassword();
    }

    public SqlDsConfig() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
