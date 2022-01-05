package com.ntnt.highblog.payment.config;

import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableFeignClients(basePackages = "com.ntnt.highblog.payment")
@EnableRetry
public class FeignConfig {
    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }
}
