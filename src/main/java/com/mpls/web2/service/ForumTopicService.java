package com.mpls.web2.service;

import com.mpls.web2.model.ForumTopic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.security.Principal;
import java.util.List;

public interface ForumTopicService {

    ForumTopic findByHeader(String header);

    ForumTopic findOneAvailable(Long id);

    ForumTopic findOne(Long id);

    ForumTopic save(ForumTopic forumTopic);

    ForumTopic save(ForumTopic forumTopic, Principal principal, Long id);

    ForumTopic update(ForumTopic forumTopic);

    Boolean delete(Long id);

    List<ForumTopic> findAllByAuthorId(Long id);

    List<ForumTopic> findAll(Sort sort);

    List<ForumTopic> findAll();

    List<ForumTopic> findAllByForumSectionId(Long id);

    List<ForumTopic> findAllAvailable();

    List<ForumTopic> findAllByAvailable(Integer pageNumber, Integer elementsNumber);

    List<ForumTopic> findAll(Pageable pageable);

    Integer countAllPages(Integer elementsOfPage);

    Integer countAllByAvailable(Boolean available);

    List<ForumTopic> findAllByAvailableByForumSectionId(Pageable pageable, Long id);

}
