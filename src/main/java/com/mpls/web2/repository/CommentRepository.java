package com.mpls.web2.repository;

import com.mpls.web2.model.Article;
import com.mpls.web2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByFrom_Id(Long id);

    List<Comment> findAllByAvailable(Boolean available);

    Comment findByAvailableAndId(Boolean available, Long id);

}
