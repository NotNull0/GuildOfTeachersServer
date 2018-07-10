package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Dto
public class ForumSectionShortDto<T extends ForumSectionShortDto> {

    protected Long id;
    protected String header;
    protected String description;
    protected Boolean available;
    protected List<ForumTopicShortDto> forumTopics;

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

    public String getDescription() {
        return description;
    }

    public T setDescription(String description) {
        this.description = description;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    public List<ForumTopicShortDto> getForumTopics() {
        return forumTopics;
    }

    public T setForumTopics(List<ForumTopicShortDto> forumTopics) {
        this.forumTopics = forumTopics;
        return (T) this;
    }

    @Override
    public String toString() {
        return "ForumSectionShortDto{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", forumTopics=" + forumTopics.stream().map(ForumTopicShortDto::getId).collect(toList()) +
                '}';
    }
}
