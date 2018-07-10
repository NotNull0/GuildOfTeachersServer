package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.Comment;

import java.util.List;

@Dto
public class ArticleFullDto extends ArticleShortDto<ArticleFullDto>{
    private List<CommentDto> comments;

    public List<CommentDto> getComments() {
        return comments;
    }

    public ArticleFullDto setComments(List<CommentDto> comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public String toString() {
        return "ArticleFullDto{" +
                "comments=" + comments +
                ", id=" + id +
                ", datetime=" + datetime +
                ", available=" + available +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
