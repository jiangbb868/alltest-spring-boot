package com.its.itone.sas.config.configure;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "itoneSqlSessionFactory")
public class DataSourceConfig {
    static final String PACKAGE = "com.its.itone.sas.config.dao.mapper";    // 放XXXMapper.java类的包
    static final String MAPPER_LOCATION = "classpath:mapping/*.xml";        // 放XXXMapper.xml配置的包

    @Value("${spring.datasource.url}")
    private String dbName1Url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Primary
    @Qualifier("itoneDataSource")
    @Bean(name = "itoneDataSource")
    public DataSource dbName1DataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(dbName1Url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        // TODO 配置的druid参数
        return druidDataSource;
    }

    @Bean(name = "itoneTransactionManager")
    public DataSourceTransactionManager dbName1TransactionManager() {
        return new DataSourceTransactionManager(dbName1DataSource());
    }

    @Primary
    @Bean(name = "itoneSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("itoneDataSource") DataSource pgDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(pgDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                                                  .getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
