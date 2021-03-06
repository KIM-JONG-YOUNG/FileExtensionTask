package edu.jong.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API Document")
				.description("프로젝트 API 문서")
				.version("1.0.0").build();
	}

	@Bean
	public Docket swaggerAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false) // 기본 상태코드를 보여줄 것인가
				.apiInfo(apiInfo())
				.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.ant("/api/**"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	}

	
	
}