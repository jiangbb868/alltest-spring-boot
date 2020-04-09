package com.its.itone.sas.config.configure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.its.itone.sas.config.dao.mapper", sqlSessionFactoryRef = "pgSqlSessionFactory")
public class PostgresConfig {

    @Value("${mybatis.mapper-locations}")
    private String MAPPER_LOCATION;
    @Value("${mybatis.type-handlers-package}")
    private String TYPE_HANDLERS_PACKAGE;

    @Bean(name = "pgSqlSessionFactory")
    public SqlSessionFactory postgresSqlSessionFactory(@Autowired DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        // case change.
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        sqlSessionFactoryBean.setTypeHandlersPackage(TYPE_HANDLERS_PACKAGE);
        return sqlSessionFactoryBean.getObject();
    }
}