package com.backend.psoft.documentation;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		        .select()
		        .apis(RequestHandlerSelectors.any())
		        .paths(PathSelectors.ant("/api/**"))
		        .build()
		        .apiInfo(buildApiInfo());
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfo(
			      "API UCDB - Classificações e reviews de cursos de Computação@UFCG",                 // Nome da API
			      "API para fornecimento de dados a cerca de disciplinas do curso de CC da UFCG",    // Descrição
			      "1.0",                                                                              // Versão
			      "",                                                                                 // URL de Termos de uso
			      new Contact("Trio parada dura solutions", "https://projsof.herokuapp.com/api/", "avaliadisciplinasufcg@gmail.com"),  // Contato
			      "Trio parada dura Solutions",                                                               // Licensa
			      "https://projsof.herokuapp.com/api/",                                                       // URL de licensa
			      Collections.emptyList()                                                                     // Extensão de fornecedores
			    );
	}

}
