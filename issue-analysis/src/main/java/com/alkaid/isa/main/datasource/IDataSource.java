package com.alkaid.isa.main.datasource;

/**
 * 数据源
 *
 * @author jiangb
 */
public interface IDataSource {

    String POSTGRESQL = "postgresql";
    String MYSQL = "mysql";
    String DB2 = "db2";
    String SQLSERVER = "sqlserver";
    String ORACLE = "oracle";

    /**
     * 从数据源中读取数据
     * @return Object 读取的数据
     */
    Object read(Object... param) throws Exception;


    /**
     * 向数据源中写数据
     * @return
     */
    int write(Object... param) throws Exception;
}
