package com.ashwetaw.config.datasource;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author heinhtet_aung
 * @created 10/11/2023
 **/
@Configuration
@EnableMongoRepositories(basePackages = "com.ashwetaw.repositories.primary",
        mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {
}
