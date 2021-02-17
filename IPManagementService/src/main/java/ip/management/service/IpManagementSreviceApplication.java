package ip.management.service;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class IpManagementSreviceApplication {


	public static void main(String[] args) {
		SpringApplication.run(IpManagementSreviceApplication.class, args);
	}

	@Bean
	public GroupedOpenApi actuatorApi() {
		return GroupedOpenApi.builder().group("Actuator").pathsToMatch("/actuator/**")
				.pathsToExclude("/actuator/health/*").build();
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption,
			@Value("${application-version}") String appVersion) {
		return new OpenAPI().addServersItem(new Server().url("http://localhost:8080").description("Local Server URL"))
				.components(new Components().addSecuritySchemes("basicScheme",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.info(new Info().title("Video Rental API").version(appVersion).description(appDesciption)
						.contact(new io.swagger.v3.oas.models.info.Contact().name("VRS Team")
								.email("jha.minakshi892@gmail.com")));
	}
}
