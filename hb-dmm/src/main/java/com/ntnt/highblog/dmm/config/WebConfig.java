package com.ntnt.highblog.dmm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig
    implements WebMvcConfigurer {

    private final ApplicationConfigProperties.FileUpload fileUploadConfigProperties;
    private final ApplicationConfigProperties.ResourceTemplate resourceTemplateConfigProperties;
    private final ApplicationConfigProperties.Cors corsConfigProperties;
    private final ResourceLoader resourceLoader;

    public WebConfig(final ApplicationConfigProperties applicationConfigProperties,
                     final ResourceLoader resourceLoader) {
        this.fileUploadConfigProperties = applicationConfigProperties.getFileUpload();
        this.resourceTemplateConfigProperties = applicationConfigProperties.getResourceTemplate();
        this.corsConfigProperties = applicationConfigProperties.getCors();
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imageRootDir = fileUploadConfigProperties.getRootDir() + "/" + fileUploadConfigProperties
            .getImagesSubDir();
        registry.addResourceHandler("static/**",
                                    fileUploadConfigProperties.getImagesSubDir() + "/**")
                .addResourceLocations("classpath:static/",
                                      "file:" + imageRootDir + "/");
    }
}
