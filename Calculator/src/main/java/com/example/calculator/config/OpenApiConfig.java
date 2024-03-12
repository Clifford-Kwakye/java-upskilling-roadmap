package com.example.calculator.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Survey Builder Team",
                        email = "surveybuilder541@gmail.com",
                        url = "http://localhost:8080"),
                description = "OpenApi documentation for Survey-Builder",
                title = "Survey Builder",
                version = "1.0",
                termsOfService = "Terms of Service"),
        servers = {
                @Server(description = "Local ENV", url = "http://localhost:8080")
        })
public class OpenApiConfig {}
