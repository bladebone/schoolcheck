package com.example.schoolcheck.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, name = "authorization")
@OpenAPIDefinition(
        info = @Info(
                title = "school check API",
                version = "0.0.1",
                description = "school check API",
                contact = @Contact(
                        name = "school check api",
                        url = "(under construct)",
                        email = "(under construct)"
                )
        ),
        security = @SecurityRequirement(name = "authorization")
)
@Configuration
public class OpenApiConfig {
}
