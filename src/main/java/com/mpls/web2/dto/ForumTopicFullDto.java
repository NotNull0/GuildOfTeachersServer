package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

import static java.util.stream.Collectors.toList;

@Dto
public class ForumTopicFullDto extends ForumTopicShortDto<ForumTopicFullDto> {

    ForumSectionShortDto forumSection;

    public ForumSectionShortDto getForumSection() {
        return forumSection;
    }

    public ForumTopicFullDto setForumSection(ForumSectionShortDto forumSection) {
        this.forumSection = forumSection;
        return this;
    }

    @Override
    public String toString() {
        return "ForumTopicFullDto{" +
                "forumSection=" + forumSection.getId() +
                ", id=" + id +
                ", header='" + header + '\'' +
                ", available=" + available +
                ", datetime=" + datetime +
                ", author=" + author.getId() +
                ", messages=" + messages.stream().map(ForumMessageDto::getId).collect(toList()) +
                ", text='" + text + '\'' +
                '}';
    }
}
