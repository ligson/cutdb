package cutdb.hb;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by trq on 2016/6/20.
 * DriverManagerdatasourceConnectionProviderImpl类是直接读取hibernate配置文件的，
 * 而DatasourcedatasourceConnectionProviderImpl是能够读取spring配置文件中的DataSource。
 */
public class SchemaBasedMultiTenantConnectionProvider implements MultiTenantConnectionProvider, InitializingBean {
    private DatasourceConnectionProviderImpl datasourceConnectionProvider = new DatasourceConnectionProviderImpl();

    public DatasourceConnectionProviderImpl getDatasourceConnectionProvider() {
        return datasourceConnectionProvider;
    }

    public void setDatasourceConnectionProvider(DatasourceConnectionProviderImpl datasourceConnectionProvider) {
        this.datasourceConnectionProvider = datasourceConnectionProvider;
    }

    public void configure(Map configurationValues) {
        this.datasourceConnectionProvider.configure(configurationValues);
    }

    public Connection getAnyConnection() throws SQLException {
        return datasourceConnectionProvider.getConnection();
    }

    public void releaseAnyConnection(Connection connection) throws SQLException {
        datasourceConnectionProvider.closeConnection(connection);
    }

    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try {
            connection.createStatement().execute("USE " + tenantIdentifier);
        } catch (SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
        }
        return connection;
    }

    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            connection.createStatement().execute("USE test");
        } catch (SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
        }
        datasourceConnectionProvider.closeConnection(connection);
    }

    public boolean supportsAggressiveRelease() {
        return this.datasourceConnectionProvider.supportsAggressiveRelease();
    }


    public void stop() {
        this.datasourceConnectionProvider.stop();
    }

    public boolean isUnwrappableAs(Class unwrapType) {
        return this.datasourceConnectionProvider.isUnwrappableAs(unwrapType);
    }

    public <T> T unwrap(Class<T> unwrapType) {
        return this.datasourceConnectionProvider.unwrap(unwrapType);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ini..................." + datasourceConnectionProvider);
    }
}
