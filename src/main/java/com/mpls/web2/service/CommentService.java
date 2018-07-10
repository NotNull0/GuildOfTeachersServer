package com.mpls.web2.service;

import com.mpls.web2.model.Comment;

import java.security.Principal;
import java.util.List;

public interface CommentService {

    List<Comment> findAllByFromId(Long id);

    Comment findOneAvailable(Long id);

    List<Comment> findAllAvailable();

    Comment findOne(Long id);

    Comment save(Comment comment);

    Comment saveArticle(Comment comment, Long id, Principal principal);

    Comment saveCourse(Comment comment,Long id, Principal principal);

    Comment update(Comment comment);

    Boolean delete(Long id);

    Boolean delete(Iterable<? extends Comment> comments);

    List<Comment> findAll();

}
