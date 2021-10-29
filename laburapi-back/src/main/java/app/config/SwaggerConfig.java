package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket personasApi() {

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("app.controller"))
				.paths(PathSelectors.regex("/laburapi/.*"))
				.build();
	}   

	    
	private ApiInfo apiInfo() {

		return new ApiInfoBuilder()
				.title("Aplicación Control horario")
				.version("1.0")
				.license("(Licencia)")
				.description("API para control horario de empleados")
				.build();
	}

}
