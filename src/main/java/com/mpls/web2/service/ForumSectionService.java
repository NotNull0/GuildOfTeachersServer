package com.mpls.web2.service;

import com.mpls.web2.model.ForumSection;
import com.mpls.web2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ForumSectionService {

    ForumSection findByHeader(String header);

    ForumSection findByDescription(String description);

    ForumSection findOneAvailable(Long id);

    ForumSection findOne(Long id);

    ForumSection save(ForumSection forumSection);

    ForumSection save(ForumSection forumSection, Long id);

    ForumSection update(ForumSection forumSection);

    ForumSection update(ForumSection forumSection, Long id);

    Boolean delete(Long id);

    List<ForumSection> findAllAvailable();

    List<ForumSection> findAllByContainerId(Long id);

    Page<ForumSection> findAll(Pageable pageable);


}
