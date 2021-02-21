package com.alkaid.isa.main.datasource;

import static java.sql.Connection.TRANSACTION_REPEATABLE_READ;

import com.alibaba.druid.util.JdbcUtils;
import com.alkaid.isa.main.exception.IsaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * 关系型数据源
 *
 * @author jiangb
 */
public class SqlDatasource implements IDataSource {

    private static Logger LOGGER = LoggerFactory.getLogger(SqlDatasource.class);
    /**
     * 数据源对象
     */
    private DataSource dataSource;

    /**
     * 数据库连接对象
     */
    private Connection connection;

    /**
     * 连接测试字串
     */
    private String testString;

    public void setTestString(String testString) {
        this.testString = testString;
    }

    /**
     * 设置数据源
     *
     * @param dataSource    数据源对象
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Object read(Object ...param) throws Exception {
        List<Map<String, Object>> list = null;
        try {
            if (param.length > 1) {
                list = this.query((String) param[0], (List<Object>) param[1]);
            } else {
                list = this.query((String) param[0], null);
            }
        } catch (IsaException sex) {
            LOGGER.debug("读取数据失败", sex);
            throw sex;
        } catch (Exception ex) {
            LOGGER.debug("读取数据失败", ex);
            throw ex;
        }
        return list;
    }

    @Override
    public int write(Object ...param) throws Exception {
        int count = 0;
        try {
            if (param.length > 1) {
                count = this.update((String) param[0], (List<Object>) param[1]);
            } else {
                count = this.update((String) param[0], null);
            }
        } catch (IsaException sex) {
            LOGGER.debug("写入数据失败", sex);
            throw sex;
        } catch (Exception ex) {
            LOGGER.debug("写入数据失败", ex);
            throw ex;
        }
        return count;
    }

    public List<Map<String, Object>> query(String sql, List<Object> param)
            throws IsaException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            List<Map<String, Object>> list = null;
            if (sql != null && sql.length() > 0) {
                connection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
                connection.setAutoCommit(false);
                if (param != null) {
                    list = JdbcUtils.executeQuery(connection, sql, param);
                } else {
                    list = JdbcUtils.executeQuery(connection, sql, new ArrayList());
                }
                connection.commit();
            }
            return list;
        } catch (SQLException sex) {
            roolback(connection);
            LOGGER.debug("执行数据库查询失败", sex);
            throw new IsaException(IsaException.MessageType.ERROR, IsaException.ERROR, "执行数据库查询失败");
        } catch (Exception ex) {
            roolback(connection);
            LOGGER.debug("执行数据库查询失败", ex);
            throw new IsaException(IsaException.MessageType.ERROR, IsaException.ERROR, "执行数据库查询失败");
        }
    }

    private void roolback(Connection connection) throws SQLException {
        if (connection != null) {
            connection.rollback();
        }
    }

    public Integer update(String sql, List<Object> param) throws IsaException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            if (sql != null && sql.length() > 0) {
                connection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
                connection.setAutoCommit(false);
                int count = 0;
                if (param != null) {
                    count = JdbcUtils.executeUpdate(connection, sql, param);
                } else {
                    count = JdbcUtils.executeUpdate(connection, sql, new ArrayList());
                }
                connection.commit();
                return count;
            } else {
                return 0;
            }
        } catch (SQLException sex) {
            connection.rollback();
            LOGGER.debug("执行数据库更新失败", sex);
            throw new IsaException(IsaException.MessageType.ERROR, IsaException.ERROR, "执行数据库更新失败");
        } catch (Exception ex) {
            connection.rollback();
            LOGGER.debug("执行数据库更新失败", ex);
            throw new IsaException(IsaException.MessageType.ERROR, IsaException.ERROR, "执行数据库更新失败");
        }
    }

    public void insert(String sql, List<Object> param) throws IsaException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            if (sql != null && sql.length() > 0) {
                connection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
                connection.setAutoCommit(false);
                if (param != null) {
                    JdbcUtils.execute(connection, sql, param);
                } else {
                    JdbcUtils.execute(connection, sql, new ArrayList());
                }
                connection.commit();
            }
        } catch (SQLException sex) {
            connection.rollback();
            LOGGER.debug("执行数据库写入失败", sex);
            throw new IsaException(IsaException.MessageType.ERROR, IsaException.ERROR, "执行数据库写入失败");
        } catch (Exception ex) {
            connection.rollback();
            LOGGER.debug("执行数据库写入失败", ex);
            throw new IsaException(IsaException.MessageType.ERROR, IsaException.ERROR, "执行数据库写入失败");
        }
    }

}
