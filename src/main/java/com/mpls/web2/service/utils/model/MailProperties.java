package com.mpls.web2.service.utils.model;

public class MailProperties {
   private String username;
   private String password;
   private String host;
   private Integer port;

    public String getUsername() {
        return username;
    }

    public MailProperties setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MailProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getHost() {
        return host;
    }

    public MailProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public MailProperties setPort(Integer port) {
        this.port = port;
        return this;
    }

    @Override
    public String toString() {
        return "MailProperties{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
