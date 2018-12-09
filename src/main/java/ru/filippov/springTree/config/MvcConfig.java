package ru.filippov.springTree.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/csv/**").addResourceLocations("file://" + this.uploadPath +"/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
