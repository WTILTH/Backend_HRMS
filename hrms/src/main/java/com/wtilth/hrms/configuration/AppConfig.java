package com.wtilth.hrms.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppConfig {

    @Value("${secret.key}")
    private String secretKey;

}
