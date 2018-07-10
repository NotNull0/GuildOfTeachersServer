package com.mpls.web2.config;

import com.mpls.web2.service.utils.FileBuilder;
import com.mpls.web2.service.utils.GenerateUuid;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
@EnableJpaRepositories(basePackages = "com.mpls.web2.repository")
@Component
public class Beans {

    @Bean
    GenerateUuid generateUuid(){
        return new GenerateUuid();
    }

    @Bean
    FileBuilder fileBuilder(){return new FileBuilder();}
}
