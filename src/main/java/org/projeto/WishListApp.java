package org.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class WishListApp {
    public static void main(String[] args) {
        SpringApplication.run(WishListApp.class, args);
    }
}