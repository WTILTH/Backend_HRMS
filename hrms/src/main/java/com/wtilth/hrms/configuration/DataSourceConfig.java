package com.wtilth.hrms.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.datasource")
@Component
@Data
public class DataSourceConfig {

    private String url;
    private String username;
    private String password;

    // Getters and Setters
}
