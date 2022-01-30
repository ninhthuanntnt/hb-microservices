package com.ntnt.highblog.dmm.config;

import com.ntnt.highblog.dmm.security.CustomJwtGrantedAuthoritiesConverter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig
    extends WebSecurityConfigurerAdapter {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        CustomJwtGrantedAuthoritiesConverter customJwtGrantedAuthoritiesConverter = new CustomJwtGrantedAuthoritiesConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(customJwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    protected void configure(HttpSecurity http)
        throws Exception {
        http
            .authorizeRequests(authorize -> {
                authorize.antMatchers("/actuator/health",
                                      "/api/v1/profiles",
                                      "/api/v1/**").permitAll();
                authorize.antMatchers("/api/v1/user/**")
                         .authenticated();
            })
            .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> {
                httpSecurityOAuth2ResourceServerConfigurer.jwt();
            });
    }
}
