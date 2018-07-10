package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

import static java.util.stream.Collectors.toList;

@Dto
public class ForumSectionFullDto extends ForumSectionShortDto<ForumSectionFullDto> {

    private ForumSectionContainerDto container;

    public ForumSectionContainerDto getContainer() {
        return container;
    }

    public ForumSectionFullDto setContainer(ForumSectionContainerDto container) {
        this.container = container;
        return this;
    }

    @Override
    public String toString() {
        return "ForumSectionFullDto{" +
                "container=" + container.getId() +
                ", id=" + id +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", forumTopics=" + forumTopics.stream().map(ForumTopicShortDto::getId).collect(toList()) +
                '}';
    }
}
