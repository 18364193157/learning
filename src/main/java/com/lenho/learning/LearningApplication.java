package com.lenho.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author langyonghe
 */
@SpringBootApplication
public class LearningApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(LearningApplication.class);
//    }
}
