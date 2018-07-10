package com.mpls.web2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.utils.DateDeserializer;
import com.mpls.web2.model.utils.DateSerializer;
import org.codehaus.jackson.annotate.JsonProperty;

import java.sql.Timestamp;

@Dto
public class ArticleShortDto<T extends ArticleShortDto> {

    protected Long id;
    protected Timestamp datetime;
    protected Boolean available;
    protected String header;
    @JsonProperty("text")
    protected String text;
    protected String image;

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

    //@JsonFormat(pattern="dd.MM.yyyy в hh:mm")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public T setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return (T) this;
    }

    public String getText() {
        return text;
    }

    public T setText(String text) {
        this.text = text;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    public String getImage() {
        return image;
    }

    public T setImage(String image) {
        this.image = image;
        return (T) this;
    }

    @Override
    public String toString() {
        return "ArticleShortDto{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", available=" + available +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
