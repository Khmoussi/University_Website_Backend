package com.example.issatc.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import jdk.jfr.Description;

@OpenAPIDefinition(
        info=@Info(
                contact = @Contact(
                        name = "Khmoussi Aouina",
                        email = "khmoussiaouina@aiesec.net"
                ),
                description ="OpenApi documentation for Spring Issatc Service",
                title = "OpenApi specification",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:9000"
                )
        },
        security = @SecurityRequirement(
                name = "Bearer Token"
        )

)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,name="Bearer Token",in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
