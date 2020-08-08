package com.example.demo_be.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class SwaggerConfig {
    private List<Parameter> headersParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new HeaderParameter().schema(new StringSchema()).required(true).name("Authorization").description("Access token (example Bearer jkjjijqiwedijwqedweij9dwedwdwed)"));
        parameters.add(new HeaderParameter().schema(new StringSchema()).required(false).name("Accept-Language").description("Preferred languages"));
        return parameters;
    }

    @Bean
    public OpenAPI openAPI(@Nullable Info info, @Nullable ExternalDocumentation externalDocumentation) {
        OpenAPI openAPI = new OpenAPI();
        Optional.ofNullable(info).ifPresent(openAPI::setInfo);
        Optional.ofNullable(externalDocumentation).ifPresent(openAPI::setExternalDocs);
        return openAPI;
    }

    @Bean
    public OpenApiCustomiser customiser() {
        OpenApiCustomiser openApiCustomiser = openApi -> {
            openApi.getPaths()
                    .values()
                    .stream()
                    .flatMap(pathItem -> pathItem.readOperations().stream())
                    .forEach(
                            operation -> {
                                if (!"methodAutenticate".equalsIgnoreCase(operation.getSummary())) { //per escludere alcuni controller dal set di Header predefinito usare la @Operation(summary
                                    headersParameters().forEach(operation::addParametersItem);
                                }
                            }
                    );
        };


        return openApiCustomiser;
    }
}
