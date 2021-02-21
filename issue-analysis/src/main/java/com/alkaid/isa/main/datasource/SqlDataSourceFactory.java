package com.alkaid.isa.main.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class SqlDataSourceFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(SqlDataSourceFactory.class);

    public static IDataSource createSqlDataSource(SqlDsConfig sqlDsConfig) {
        StringBuilder url = new StringBuilder();
        String driverClass = null;
        switch (sqlDsConfig.getType()) {
            case IDataSource.POSTGRESQL:
                url.append("jdbc:postgresql://")
                        .append(sqlDsConfig.getHost())
                        .append(":").append(sqlDsConfig.getPort())
                        .append("/").append(sqlDsConfig.getDatabase());
                driverClass = "org.postgresql.Driver";
                break;
            case IDataSource.ORACLE:
                //jdbc:oracle:thin:@//127.0.0.1:1521/orcl
                url.append("jdbc:oracle:thin:@//")
                        .append(sqlDsConfig.getHost())
                        .append(":").append(sqlDsConfig.getPort())
                        .append("/").append(sqlDsConfig.getDatabase());
                driverClass = "oracle.jdbc.driver.OracleDriver";
                break;
            case IDataSource.MYSQL:
                //"jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8"
                url.append("jdbc:mysql://").append(sqlDsConfig.getHost())
                        .append(":").append(sqlDsConfig.getPort())
                        .append("/").append(sqlDsConfig.getDatabase())
                        .append("?useUnicode=true&characterEncoding=utf-8");
                driverClass = "com.mysql.cj.jdbc.Driver";
                break;
            case IDataSource.DB2:
                //jdbc:db2://host-name:50000/BLUDB
                url.append("jdbc:db2://").append(sqlDsConfig.getHost())
                        .append(":").append(sqlDsConfig.getPort())
                        .append("/").append(sqlDsConfig.getDatabase());
                driverClass = "com.ibm.db2.jcc.DB2Driver";
                break;
            case IDataSource.SQLSERVER:
                //jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=sample
                url.append("jdbc:microsoft:sqlserver://").append(sqlDsConfig.getHost())
                        .append(":").append(sqlDsConfig.getPort())
                        .append("; DatabaseName=").append(sqlDsConfig.getDatabase());
                driverClass = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
                break;
        }
        DataSource dataSource = createDataSource(url.toString(), driverClass, sqlDsConfig.getUser(), sqlDsConfig.getPassword());
        SqlDatasource sqlDatasource = new SqlDatasource();
        sqlDatasource.setDataSource(dataSource);
        return sqlDatasource;
    }

    private static DataSource createDataSource(String url, String driverClass, String user, String password) {
        DruidDataSource dataSource;
        try {
            dataSource = new DruidDataSource();
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            dataSource.setUrl(url);
            dataSource.setDriverClassName(driverClass);
            dataSource.setFilters("stat");
            dataSource.setInitialSize(1);
            dataSource.setMaxActive(2);
            dataSource.setMinIdle(1);
            dataSource.setMaxWait(60000);
            dataSource.setTimeBetweenEvictionRunsMillis(3000);
            dataSource.setMinEvictableIdleTimeMillis(300000);
            dataSource.setValidationQuery("SELECT 'X' ");
            dataSource.setTestWhileIdle(true);
            dataSource.setTestOnBorrow(false);
            dataSource.setPoolPreparedStatements(true);
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(2);
            dataSource.setMaxOpenPreparedStatements(5);
        } catch (Exception e) {
            LOGGER.error("创建数据源异常" + e);
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
