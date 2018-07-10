package com.mpls.web2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.utils.DateDeserializer;
import com.mpls.web2.model.utils.DateSerializer;

import java.sql.Timestamp;

@Dto
public class LawShortDto<T extends LawShortDto> {

    protected Long id;
    protected Timestamp datetime;
    protected String name;
    protected String path;
    protected Boolean available;

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public T setName(String name) {
        this.name = name;
        return (T) this;
    }

    public String getPath() {
        return path;
    }

    public T setPath(String path) {
        this.path = path;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    @Override
    public String toString() {
        return "LawShortDto{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", available=" + available +
                '}';
    }
}
