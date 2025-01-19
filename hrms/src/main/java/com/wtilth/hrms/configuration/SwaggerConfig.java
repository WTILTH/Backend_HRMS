package com.wtilth.hrms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.GroupedOpenApi;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi hrmsApi() {
        return GroupedOpenApi.builder()
                .group("HRMS API")
                .packagesToScan("com.wtilth")  // Your base package
                .pathsToMatch("/**")
                .build();
    }
}
