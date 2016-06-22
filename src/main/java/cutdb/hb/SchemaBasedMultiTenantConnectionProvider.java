package cutdb.hb;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    private void useDb(String dbName, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT count(sc.SCHEMA_NAME) FROM information_schema.SCHEMATA sc where sc.SCHEMA_NAME=?");
            ps.setString(1, dbName);
            ResultSet rs = ps.executeQuery();
            boolean exist = true;
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    connection.createStatement().execute("create database " + dbName + " DEFAULT  character set  utf8");
                    exist = false;
                }
            }
            connection.createStatement().execute("use " + dbName);
            if (!exist) {
                ClassPathResource cpr = new ClassPathResource("initDb.sql");
                try {
                    File file = cpr.getFile();
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("--") || line.startsWith("#")) {
                            continue;
                        }
                        logger.info("执行sql语句:{}", line);
                        connection.createStatement().execute(line);
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        useDb(tenantIdentifier, connection);
        return connection;
    }

    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        useDb("test", connection);
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
