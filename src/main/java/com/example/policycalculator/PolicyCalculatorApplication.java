package com.example.policycalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class PolicyCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolicyCalculatorApplication.class, args);
    }

}
