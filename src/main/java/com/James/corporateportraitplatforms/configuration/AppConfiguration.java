package com.James.corporateportraitplatforms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sweeneyhe.Ml;

@Configuration
public class AppConfiguration {

    @Bean
    public Ml ml() {
        return new Ml("com/James/corporateportraitplatforms/modelNa");
    }
}
