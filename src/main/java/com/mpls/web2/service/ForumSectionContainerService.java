package com.mpls.web2.service;

import com.mpls.web2.model.ForumSectionContainer;

import java.util.List;

public interface ForumSectionContainerService {

    ForumSectionContainer findByHeader(String header);

    ForumSectionContainer findOneAvailable(Long id);

    ForumSectionContainer findOne(Long id);

    ForumSectionContainer save(ForumSectionContainer forumSectionContainer);

    ForumSectionContainer update(ForumSectionContainer forumSectionContainer);

    Boolean delete(Long id);

    List<ForumSectionContainer> findAllAvailable();

    List<ForumSectionContainer> findAll();

    ForumSectionContainer findBySectionId(Long id);

}
