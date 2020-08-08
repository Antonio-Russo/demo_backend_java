package com.example.demo_be;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {

		Assert.notNull("${APP_NAME}", "Please insert enviroment variable APP_NAME");
		Assert.notNull("${APP_VERSION}", "Please insert enviroment variable APP_VERSION");

		SpringApplication.run(MainApplication.class, args);
	}

	//link swagger openApi http://localhost:8080/swagger-ui/index.html?url=/v3/api-docs
	//http://localhost:8080/v3/api-docs
	@Bean
	public OpenAPI customOpenAPI(@Value("${APP_NAME}") String appDescription, @Value("${APP_VERSION}") String appVersion) {
		return new OpenAPI()
				.info(new Info()
						.title(appDescription)
						.version(appVersion)
						.description(appDescription)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
