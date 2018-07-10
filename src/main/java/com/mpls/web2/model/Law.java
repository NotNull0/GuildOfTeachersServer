package com.mpls.web2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.model.utils.DateSerializer;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Law {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp datetime;
    private String name;
    private String path;
    private Boolean available = true;
    @ManyToOne
    private LawContainer container;

    public Law() {
    }

    public Long getId() {
        return id;
    }

    public Law setId(Long id) {
        this.id = id;
        return this;
    }

    //    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    public Law setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getName() {
        return name;
    }

    public Law setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Law setPath(String path) {
        this.path = path;
        return this;
    }

    public LawContainer getContainer() {
        return container;
    }

    public Law setContainer(LawContainer container) {
        this.container = container;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Law setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "Law{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", container=" + container.getName() +
                ", available=" + available +
                '}';
    }
}
