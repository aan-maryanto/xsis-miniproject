package com.example.xsisminiproject;

import com.example.xsisminiproject.service.FileServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class XsisMiniprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(XsisMiniprojectApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FileServiceImp fileServiceImp) {
        return (args) -> {
            fileServiceImp.deleteAll();
            fileServiceImp.init();
        };
    }

}
