package com.ntnt.highblog.uaa.config;

import com.ntnt.highblog.uaa.security.CustomJwtGrantedAuthoritiesConverter;
import com.ntnt.highblog.uaa.security.CustomLogoutSuccessHandler;
import com.ntnt.highblog.uaa.security.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailService customUserDetailService;

    public SecurityConfig(final CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
        throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                                    authorizeRequests.antMatchers("/api/v1/register/**").permitAll()
                                                     .anyRequest().authenticated()
            )
            .logout()
            .logoutSuccessHandler(new CustomLogoutSuccessHandler())
            .and()
            .formLogin(Customizer.withDefaults()).oauth2ResourceServer().jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter());
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailService;
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        CustomJwtGrantedAuthoritiesConverter customJwtGrantedAuthoritiesConverter = new CustomJwtGrantedAuthoritiesConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(customJwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    DaoAuthenticationConfigurer daoAuthenticationConfigurer()
        throws Exception {
        return new DaoAuthenticationConfigurer(userDetailsService());
    }

//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.withUsername("hb_admin")
//                               .username("hb_admin")
//                               .passwordEncoder(s -> passwordEncoder().encode("hb_admin"))
//                               .roles("admin")
//                               .build();
//        return new InMemoryUserDetailsManager(user);
//    }


//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean()
//        throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http)
//        throws Exception {
//
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        http
//            .authorizeRequests(authorizeRequests ->
//                                   authorizeRequests.anyRequest().authenticated()
//            )
//            .formLogin(Customizer.withDefaults());
//        http.csrf().disable();
//    }
}
