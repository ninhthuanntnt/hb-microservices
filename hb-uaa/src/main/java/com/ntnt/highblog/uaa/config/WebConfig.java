package com.ntnt.highblog.uaa.config;

import com.ntnt.highblog.uaa.helper.FileHelper;
import com.ntnt.highblog.uaa.security.CustomAuditorAware;
import org.hibernate.internal.FilterHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class WebConfig {
    private final ResourceLoader resourceLoader;

    public WebConfig(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public String registrationEmailTemplate() {
        Resource resource = resourceLoader.getResource("classpath:./templates/registration_email_template.html");
        return FileHelper.asString(resource);
    }

    @Bean
    public AuditorAware<String> auditorAware(){
        return new CustomAuditorAware();
    }
}
