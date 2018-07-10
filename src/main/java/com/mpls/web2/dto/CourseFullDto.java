package com.mpls.web2.dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CourseFullDto extends CourseShortDto<CourseFullDto> {

    private List<CommentDto> comments;

    public List<CommentDto> getComments() {
        return comments;
    }

    public CourseFullDto setComments(List<CommentDto> comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public String toString() {
        return "CourseFullDto{" +
                "comments=" + comments.stream().map(CommentDto::getId).collect(toList()) +
                ", id=" + id +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", type=" + type +
                ", available=" + available +
                ", courseCategory=" + courseCategory.getName() +
                ", link='" + link + '\'' +
                ", canVote=" + canVote +
                ", userRating=" + userRating +
                '}';
    }
}
