package com.mugoto.todoapp.shared.auth.config;

import com.mugoto.todoapp.shared.auth.filters.AuthFilter;
import com.mugoto.todoapp.shared.auth.properties.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {
    private final AuthProperties authProperties;

    @Autowired
    public AuthConfig(AuthProperties AuthProperties) {
        this.authProperties = AuthProperties;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter(authProperties));
        registrationBean.addUrlPatterns("/api/todolists/*");
        return registrationBean;
    }
}
