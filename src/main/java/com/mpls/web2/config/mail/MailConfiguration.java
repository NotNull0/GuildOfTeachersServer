package com.mpls.web2.config.mail;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.thymeleaf.TemplateEngine;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    public static JavaMailSender generateMailSender(String username, String password, String host, Integer port) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.quitwait", "true");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.socketFactory.port", port);
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        javaMailProperties.put("mail.default-encoding", "UTF-8");
        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        javaMailProperties.put("mail.smtp.ssl.trust", "*");
        mailSender.setJavaMailProperties(javaMailProperties);
        mailSender.setDefaultEncoding("UTF-8");
        return mailSender;
    }

    public static JavaMailSender generateMailSender(MailProperties mailProperties) {
        return generateMailSender(mailProperties.getUsername(), mailProperties.getPassword(), mailProperties.getHost(), mailProperties.getPort());
    }

    @Bean
    public JavaMailSender getMailSender() {
        return generateMailSender(username, password, host, port);
    }

    @Bean
    @Qualifier("freemarkerConf")
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        System.err.println("-------------------------------------------------------------");
        System.err.println("-------------------------------------------------------------");
        System.err.println("file:" + System.getProperty("catalina.home"));
        System.err.println("-------------------------------------------------------------");
        System.err.println("-------------------------------------------------------------");
        System.err.println("classpath:");
        System.err.println("-------------------------------------------------------------");
        System.err.println("-------------------------------------------------------------");
        bean.setDefaultEncoding("UTF-8");
        bean.setTemplateLoaderPaths(
                "file:" + System.getProperty("catalina.home"),
                "classpath:/templates"
        );
        return bean;
    }

    @Bean
    public TemplateEngine templateEngine() {
        return new TemplateEngine();
    }

}
