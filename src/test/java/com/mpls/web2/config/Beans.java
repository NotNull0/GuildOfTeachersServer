package com.mpls.web2.config;

import com.mpls.web2.service.ArticleService;
import com.mpls.web2.service.impl.ArticleServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@EnableJpaRepositories(basePackages = "com.mpls.web2.repository")
@TestConfiguration
@ActiveProfiles("test")
public class Beans {

}