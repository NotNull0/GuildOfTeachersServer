package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.enums.FileType;

@Dto
public class FileDto {

    private Long id;
    private String name;
    private String path;
    private Boolean available;
    private FileType fileType;

    public Long getId() {
        return id;
    }

    public FileDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FileDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public FileDto setPath(String path) {
        this.path = path;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public FileDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public FileType getFileType() {
        return fileType;
    }

    public FileDto setFileType(FileType fileType) {
        this.fileType = fileType;
        return this;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", available=" + available +
                ", fileType=" + fileType +
                '}';
    }
}
