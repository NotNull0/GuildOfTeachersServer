package com.mpls.web2.repository;

import com.mpls.web2.model.ForumMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumMessageRepository extends JpaRepository<ForumMessage, Long> {

    List<ForumMessage> findAllByFrom_Id(Long id);

    ForumMessage findByAvailableAndId(Boolean available, Long id);

    List<ForumMessage> findAllByAvailable(Boolean available);

    Page<ForumMessage> findAll(Pageable pageable);

    Page<ForumMessage> findAllByForumTopic_IdAndAvailable(Pageable pageable, Long id, Boolean available);

}
