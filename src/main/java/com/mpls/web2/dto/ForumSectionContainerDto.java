package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Dto
public class ForumSectionContainerDto {

    private Long id;
    private String header;
    private Boolean available;
    private List<ForumSectionShortDto> sections;

    public Long getId() {
        return id;
    }

    public ForumSectionContainerDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public ForumSectionContainerDto setHeader(String header) {
        this.header = header;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ForumSectionContainerDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<ForumSectionShortDto> getSections() {
        return sections;
    }

    public ForumSectionContainerDto setSections(List<ForumSectionShortDto> sections) {
        this.sections = sections;
        return this;
    }

    @Override
    public String toString() {
        return "ForumSectionContainerDto{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", available=" + available +
                ", sections=" + sections.stream().map(ForumSectionShortDto::getId).collect(toList()) +
                '}';
    }
}
