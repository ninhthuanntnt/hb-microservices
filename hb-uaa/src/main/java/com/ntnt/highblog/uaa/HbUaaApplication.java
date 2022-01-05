package com.ntnt.highblog.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HbUaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbUaaApplication.class, args);
    }

}
