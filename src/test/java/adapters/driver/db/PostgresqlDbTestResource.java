package adapters.driver.db;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

public class PostgresqlDbTestResource implements QuarkusTestResourceLifecycleManager {

    PostgreSQLContainer container;

    @Override
    public Map<String, String> start() {
        container = new PostgreSQLContainer<>("postgres:17.0-alpine")
                .withDatabaseName("teste")
                .withUsername("postgres")
                .withPassword("postgres");
        container.start();
        Map<String, String> conf = new HashMap<>();
        conf.put("quarkus.datasource.db-kind", "postgresql");
        conf.put("quarkus.datasource.jdbc.url", container.getJdbcUrl());
        conf.put("quarkus.datasource.username", container.getUsername());
        conf.put("quarkus.datasource.password", container.getPassword());
        return conf;
    }

    @Override
    public void stop() {
        container.stop();

    }
}
