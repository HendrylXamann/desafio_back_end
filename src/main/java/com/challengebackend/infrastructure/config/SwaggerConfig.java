package com.challengebackend.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Challenge Backend API",
                version = "1.0",
                description = "API for managing players, tournaments, and challenges"
        )
)
public class SwaggerConfig {
}
