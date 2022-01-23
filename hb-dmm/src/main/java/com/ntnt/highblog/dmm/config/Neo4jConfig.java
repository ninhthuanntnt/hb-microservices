package com.ntnt.highblog.dmm.config;

import org.neo4j.driver.Driver;
import org.neo4j.driver.internal.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.neo4j.Neo4jAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(
    basePackages = "com.ntnt.highblog.dmm.neo4j.repository",
    transactionManagerRef = "neo4jTransactionManager"
)
public class Neo4jConfig {

    @Bean("neo4jTransactionManager")
    @Qualifier("neo4jTransactionManager")
    public Neo4jTransactionManager neo4jTransactionManager(Driver driver) {
        return new Neo4jTransactionManager(driver);
    }
}
