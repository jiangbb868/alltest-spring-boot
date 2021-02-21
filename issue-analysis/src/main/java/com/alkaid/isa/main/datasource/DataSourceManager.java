package com.alkaid.isa.main.datasource;

import com.alkaid.isa.config.api.IDataSourceService;
import com.alkaid.isa.main.exception.IsaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alkaid.isa.config.pojo.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据源管理器
 *
 * @author jiangb
 */
public class DataSourceManager {
    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceManager.class);
    /**
     * 数据源容器map
     */
    private Map<String, IDataSource> dataSourceMap = new ConcurrentHashMap<>();

    private String defaultDatasourceKey;

    @Autowired
    private IDataSourceService dataSourceService;

    /**
     * 注册数据源
     *
     * @param key         数据源唯一标识key
     * @param dataSource  数据源对象
     */
    public boolean registry(String key, IDataSource dataSource) {
        if (key != null && dataSource != null) {
            dataSourceMap.put(key, dataSource);
            return true;
        }
        return false;
    }

    /**
     * 获取数据源
     *
     * @param key   数据源唯一标识key
     * @return      数据源对象
     */
    public synchronized IDataSource get(String key) throws IsaException {
        if (this.dataSourceMap.containsKey(key)) {
            return this.dataSourceMap.get(key);
        } else {
            IDataSource iDataSource = null;
            try {
                DataSource dataSource = dataSourceService.getById(key);
                if (dataSource != null) {
                    iDataSource = SqlDataSourceFactory.createSqlDataSource(new SqlDsConfig(dataSource));
                    if (iDataSource != null) {
                        registry(key, iDataSource);
                    }
                } else {
                    LOGGER.error("数据源配置不存在，"+key);
                }
            } catch (Exception ex) {
                LOGGER.error("创建数据源发生异常" + ex);
                throw new IsaException(ex.getMessage());
            }
            if (iDataSource == null) {
                LOGGER.error("创建数据源失败：" + key);
            }
            return iDataSource;
        }
    }

    public void setDefaultDatasourceKey(String key) {
        this.defaultDatasourceKey = key;
    }

    public IDataSource getDefaultDatasource() {
        return dataSourceMap.get(this.defaultDatasourceKey);
    }
}
