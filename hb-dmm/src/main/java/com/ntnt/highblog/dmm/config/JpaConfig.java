package com.ntnt.highblog.dmm.config;

import com.ntnt.highblog.dmm.security.CustomAuditorAware;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.ntnt.highblog.dmm.repository",
    transactionManagerRef = "transactionManager"
)
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware(){
        return new CustomAuditorAware();
    }

    @Bean("transactionManager")
    @Qualifier("transactionManager")
    @Primary
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
