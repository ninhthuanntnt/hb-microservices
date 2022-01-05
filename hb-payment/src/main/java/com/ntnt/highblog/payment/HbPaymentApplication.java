package com.ntnt.highblog.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class HbPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbPaymentApplication.class, args);
    }

}
