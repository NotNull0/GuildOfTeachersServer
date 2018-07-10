package com.mpls.web2.dto;

public class EmailMessage {
    private String from;
    private String to;
    private String cc;
    private String subject;
    private String date;
    private String message;

    public String getFrom() {
        return from;
    }

    public EmailMessage setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public EmailMessage setTo(String to) {
        this.to = to;
        return this;
    }

    public String getCc() {
        return cc;
    }

    public EmailMessage setCc(String cc) {
        this.cc = cc;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public EmailMessage setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDate() {
        return date;
    }

    public EmailMessage setDate(String date) {
        this.date = date;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public EmailMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "from='" + from + '\'' + ",\n" +
                "to='" + to + '\'' + ",\n" +
                "cc='" + cc + '\'' + ",\n" +
                "subject='" + subject + '\'' + ",\n" +
                "date='" + date + '\'' + ",\n" +
                "message='" + message + '\'' +
                '}';
    }
}
