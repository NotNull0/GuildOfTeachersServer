package com.mpls.web2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.utils.DateDeserializer;
import com.mpls.web2.model.utils.DateSerializer;

import java.sql.Timestamp;
import java.util.List;

@Dto
public class ForumMessageDto {

    private Long id;
    private Timestamp datetime;
    private Boolean available;
    private String text;
    private UserShortDto from;
    private List<FileDto> files;

    public Long getId() {
        return id;
    }

    public ForumMessageDto setId(Long id) {
        this.id = id;
        return this;
    }

    //@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public ForumMessageDto setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ForumMessageDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getText() {
        return text;
    }

    public ForumMessageDto setText(String text) {
        this.text = text;
        return this;
    }

    public UserShortDto getFrom() {
        return from;
    }

    public ForumMessageDto setFrom(UserShortDto from) {
        this.from = from;
        return this;
    }

    public List<FileDto> getFiles() {
        return files;
    }

    public ForumMessageDto setFiles(List<FileDto> files) {
        this.files = files;
        return this;
    }

    @Override
    public String toString() {
        return "ForumMessageDto{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", available=" + available +
                ", text='" + text + '\'' +
                ", from=" + from +
                ", files=" + files +
                '}';
    }
}
