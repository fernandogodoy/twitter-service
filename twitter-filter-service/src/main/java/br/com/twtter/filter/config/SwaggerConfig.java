package br.com.twtter.filter.config;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.swagger.annotations.Api;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	
	@Bean
	public Docket api() {
		return new Docket(SWAGGER_2).select()
				.apis(withClassAnnotation(Api.class))
				.paths(any())
				.build()
				.pathMapping("/")
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false);
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Twitter Service Filter API")
				.version("1.0-SNAPSHOT")
				.license("(C) Copyright")
				.description("Endpoints used in Twitter Service Filter API")
				.build();
	}

}
