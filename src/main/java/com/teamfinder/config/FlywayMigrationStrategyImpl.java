package com.teamfinder.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

@Component
public class FlywayMigrationStrategyImpl implements FlywayMigrationStrategy {

    @Value("${flyway.url}")
    private String DATABASE_URL;

    @Value("${flyway.user}")
    private String DATABASE_USERNAME;

    @Value("${flyway.password}")
    private String DATABASE_PASSWORD;

    @Value("${flyway.defaultSchema}")
    private String DATABASE_SCHEMA_NAME;
    
    @Override
    public void migrate(Flyway flyway) {
        Flyway.configure()
                .dataSource(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD)
                .schemas(DATABASE_SCHEMA_NAME)
                .defaultSchema(DATABASE_SCHEMA_NAME)
                .baselineOnMigrate(true)
                .load()
                .migrate();
    }
}
