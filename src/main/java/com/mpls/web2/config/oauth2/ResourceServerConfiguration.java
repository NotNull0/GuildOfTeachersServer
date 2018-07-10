package com.mpls.web2.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import static com.mpls.web2.config.oauth2.AuthorizationServerConfiguration.RESOURCE_ID;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET, "/user").authenticated()
//                .antMatchers("/auth/**").authenticated()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/oauth/token").permitAll()
                //ARTICLE
                .antMatchers(HttpMethod.POST, "/article/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/article/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.GET, "/article/**").permitAll()
                .antMatchers("/article/find-one-available/**").permitAll()
                //CATEGORY
                .antMatchers(HttpMethod.POST, "/category/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/category/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.GET, "/category/**").permitAll()
                .antMatchers("/category/find-one-available/**").permitAll()
                //COURSE-CATEGORY
                .antMatchers(HttpMethod.POST, "/course-category/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/course-category/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.GET, "/course-category/**").permitAll()
                .antMatchers("/course-category/find-one-available/**").permitAll()
                //COURSE
                .antMatchers("/course/vote/**").authenticated()
                .antMatchers(HttpMethod.POST, "/course/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/course/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.GET, "/course/**").permitAll()
                .antMatchers("/course/find-one-available/**").permitAll()
                //FORUM-SECTION-CONTAINER
                .antMatchers(HttpMethod.POST, "/forum-section-container/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/forum-section-container/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.GET, "/forum-section-container/**").permitAll()
                .antMatchers("/forum-section-container/find-one-available/**").permitAll()
                //FORUM-SECTION
                .antMatchers(HttpMethod.POST, "/forum-section/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/forum-section/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.GET, "/forum-section/**").permitAll()
                //FORUM-SECTION
                .antMatchers(HttpMethod.POST, "/forum-topic/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/forum-topic/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.GET, "/forum-topic/**").permitAll()
                .antMatchers("/forum-topic/find-one-available/**").permitAll()
                ///law-container
                .antMatchers(HttpMethod.POST, "/law-container/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/law-container/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.GET, "/law-container/**").permitAll()
                .antMatchers("/law-container/find-one-available/**").permitAll()
                ///law
                .antMatchers(HttpMethod.POST, "/law/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/law/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.GET, "/law/**").access("hasAuthority('MODERATOR')")
                .antMatchers("/law/find-one-available/**").permitAll()
                //TEACHING-PROGRAM-CONTAINER
                .antMatchers(HttpMethod.POST, "/teaching-program-container/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/teaching-program-container/**").access("hasAuthority('MODERATOR')")
                //TEACHING-PROGRAM
                .antMatchers(HttpMethod.POST, "/teaching-program/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/teaching-program/**").access("hasAuthority('MODERATOR')")
                //questionnaire
                .antMatchers(HttpMethod.POST, "/questionnaire/**").access("hasAuthority('MODERATOR')")
                .antMatchers(HttpMethod.DELETE, "/questionnaire/**").access("hasAuthority('MODERATOR')")
                //PLACE OF WORK
                .antMatchers(HttpMethod.POST, "/place-of-work/**").access("hasAuthority('USER')")
                .antMatchers(HttpMethod.DELETE, "/place-of-work/**").access("hasAuthority('USER')")
                //specialization
                .antMatchers(HttpMethod.POST, "/specialization/**").access("hasAuthority('USER')")
                .antMatchers(HttpMethod.DELETE, "/specialization/**").access("hasAuthority('USER')")
//                .antMatchers("/test").authenticated()
                .antMatchers("/add").permitAll()
//                .antMatchers("/user/get-user").authenticated()
                .anyRequest().permitAll();
    }


}