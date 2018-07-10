package com.mpls.web2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.utils.DateDeserializer;
import com.mpls.web2.model.utils.DateSerializer;

import java.sql.Timestamp;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Dto
public class ForumTopicShortDto<T extends ForumTopicShortDto> {

    protected Long id;
    protected String header;
    protected Boolean available;
    protected Timestamp datetime;
    protected UserShortDto author;
    protected List<ForumMessageDto> messages;
    protected String text;

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public String getHeader() {
        return header;
    }

    public T setHeader(String header) {
        this.header = header;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    //@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public T setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return (T) this;
    }

    public UserGeneralInfoDto getAuthor() {
        return author;
    }


    public T setAuthor(UserShortDto author) {
        this.author = author;
        return (T) this;
    }

    public List<ForumMessageDto> getMessages() {
        return messages;
    }

    public T setMessages(List<ForumMessageDto> messages) {
        this.messages = messages;
        return (T) this;
    }

    public String getText() {
        return text;
    }

    public T setText(String text) {
        this.text = text;
        return (T) this;
    }

    @Override
    public String toString() {
        return "ForumTopicShortDto{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", available=" + available +
                ", datetime=" + datetime +
                ", author=" + author.getId() +
                ", messages=" + messages.stream().map(ForumMessageDto::getId).collect(toList()) +
                ", text='" + text + '\'' +
                '}';
    }
}
