package com.fee.feeSecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcCfg extends WebMvcConfigurerAdapter {
//public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/index").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/user").setViewName("user");



        registry.addViewController("/accountant").setViewName("accountant");//
        registry.addViewController("/accountant/student-edit/{id}").setViewName("acc-student-edit");//AccC
        registry.addViewController("/student").setViewName("student");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/search").setViewName("users");
        registry.addViewController("/admin/{id}").setViewName("admin-edit");
        registry.addViewController("/edit/{id}").setViewName("edit");
        registry.addViewController("/error").setViewName("error");
    }
}
