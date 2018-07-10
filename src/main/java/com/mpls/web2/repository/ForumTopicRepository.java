package com.mpls.web2.repository;

import com.mpls.web2.model.ForumTopic;
import com.mpls.web2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumTopicRepository extends JpaRepository<ForumTopic, Long> {

    ForumTopic findByHeader(String header);

    List<ForumTopic> findAllByAuthor_Id(Long id);

    List<ForumTopic> findAll(Sort sort);

    List<ForumTopic> findAll();

    List<ForumTopic> findAllByForumSection_Id(Long id);

    List<ForumTopic> findAllByAvailable(Boolean available);

    ForumTopic findByAvailableAndId(Boolean available, Long id);

    Page<ForumTopic> findAllByAvailable(Pageable pageable,Boolean available);

    Integer countAllByAvailable(Boolean available);

    Page<ForumTopic> findAll(Pageable pageable);

    Page<ForumTopic> findAllByAvailableAndForumSection_Id(Boolean available,Long id,Pageable pageable);


}
