package cutdb.hb;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by trq on 2016/6/20.
 * DriverManagerdatasourceConnectionProviderImpl类是直接读取hibernate配置文件的，
 * 而DatasourcedatasourceConnectionProviderImpl是能够读取spring配置文件中的DataSource。
 */

public class SchemaBasedMultiTenantConnectionProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService {
    private static Logger logger = LoggerFactory.getLogger(SchemaBasedMultiTenantConnectionProvider.class);
    private DatasourceConnectionProviderImpl datasourceConnectionProvider = new DatasourceConnectionProviderImpl();

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
        return true;
    }


    public void stop() {
        datasourceConnectionProvider.stop();
    }

    public boolean isUnwrappableAs(Class unwrapType) {
        return datasourceConnectionProvider.isUnwrappableAs(unwrapType);
    }

    public <T> T unwrap(Class<T> unwrapType) {
        return datasourceConnectionProvider.unwrap(unwrapType);
    }


    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        Map settings = serviceRegistry.getService(ConfigurationService.class).getSettings();
        DataSource dataSource = (DataSource) settings.get(Environment.DATASOURCE);
        datasourceConnectionProvider.setDataSource(dataSource);
        datasourceConnectionProvider.configure(settings);
        logger.debug("connection provider:{}", datasourceConnectionProvider);
    }
}
