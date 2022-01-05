package com.ntnt.highblog.gateway.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
@NoArgsConstructor
@Getter
@Setter
public class ApplicationProperties {
    private Uri uri;

    @Getter
    @Setter
    public static class Uri {
       private String frontEndUser;
    }
}
