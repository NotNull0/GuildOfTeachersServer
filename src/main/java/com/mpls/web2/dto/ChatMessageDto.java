package com.mpls.web2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.utils.DateDeserializer;
import com.mpls.web2.model.utils.DateSerializer;
import org.codehaus.jackson.annotate.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Dto
public class ChatMessageDto {

    private Long id;
    private Timestamp datetime;
    @JsonProperty("text")
    private String text;
    private Boolean available;
    private Boolean hasBeenRead;
    private UserGeneralInfoDto from;
    private List<FileDto> files;

    public Long getId() {
        return id;
    }

    public ChatMessageDto setId(Long id) {
        this.id = id;
        return this;
    }

    //@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public ChatMessageDto setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getText() {
        return text;
    }

    public ChatMessageDto setText(String text) {
        this.text = text;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ChatMessageDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public UserGeneralInfoDto getFrom() {
        return from;
    }

    public ChatMessageDto setFrom(UserGeneralInfoDto from) {
        this.from = from;
        return this;
    }

    public List<FileDto> getFiles() {
        return files;
    }

    public ChatMessageDto setFiles(List<FileDto> files) {
        this.files = files;
        return this;
    }

    public Boolean getHasBeenRead() {
        return hasBeenRead;
    }

    public ChatMessageDto setHasBeenRead(Boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
        return this;
    }

    @Override
    public String toString() {
        return "ChatMessageDto{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", text='" + text + '\'' +
                ", available=" + available +
                ", hasBeenRead=" + hasBeenRead +
                ", from=" + from.getId() +
                ", files=" + files.stream().map(FileDto::getId).collect(toList()) +
                '}';
    }
}
