package com.luoromeo.ccm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcmApplication.class, args);
    }
}
