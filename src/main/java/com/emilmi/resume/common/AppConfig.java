package com.emilmi.resume.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Common<?> common() {
        return new Common<>();
    }
}
