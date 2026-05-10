package com.ecommerce.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ecommerce.demo")
@EntityScan(basePackages = "com.ecommerce.demo.model")
@EnableJpaRepositories(basePackages = "com.ecommerce.demo.repository")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("========================================");
        System.out.println("ShopEase Server Started Successfully!");
        System.out.println("========================================");
        System.out.println("Website: http://localhost:8080");
        System.out.println("API: http://localhost:8080/api/products");
        System.out.println("Orders API: http://localhost:8080/api/orders");
        System.out.println("H2 Console: http://localhost:8080/h2-console");
        System.out.println("========================================");
    }
}
