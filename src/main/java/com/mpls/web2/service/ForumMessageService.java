package com.mpls.web2.service;

import com.mpls.web2.model.ForumMessage;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface ForumMessageService {

    List<ForumMessage> findAllByFromId(Long id);

    ForumMessage findOneAvailable(Long id);

    List<ForumMessage> findAllAvailable();

    ForumMessage findOne(Long id);

    ForumMessage save(ForumMessage forumMessage);

    ForumMessage save(ForumMessage forumMessage, Principal principal);

    ForumMessage save(ForumMessage forumMessage, Principal principal, Long id);

    ForumMessage update(ForumMessage forumMessage);

    Boolean delete(Long id);

    Boolean delete(Iterable<? extends ForumMessage> forumMessages);

    List<ForumMessage> findAll();

    List<ForumMessage> findAll(Pageable pageable);

    List<ForumMessage> findAllByForumTopicId(Pageable pageable, Long id);


}
