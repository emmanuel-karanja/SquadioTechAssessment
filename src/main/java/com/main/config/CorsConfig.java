package com.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Value("cors.allowedOrigins")
    private String allowedOrigins;

    public void addCorsMappings(CorsRegistry registry){
        final long MAX_AGE_IN_SECS=3600;

        registry.addMapping("/**")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("*")
                .maxAge(MAX_AGE_IN_SECS);

    }
}

