package com.backend.psoft;


import com.backend.psoft.authentication.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class ProjetoPsoftApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPsoftApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterJwt() {
		FilterRegistrationBean filterRb = new FilterRegistrationBean();
		filterRb.setFilter(new TokenFilter());
		filterRb.addUrlPatterns("/subjects/search/");
		return filterRb;
	}
}
