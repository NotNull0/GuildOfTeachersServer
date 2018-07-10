package com.mpls.web2.repository;

import com.mpls.web2.model.ChatUserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatUserWrapperRepository extends JpaRepository<ChatUserWrapper, Long> {

    ChatUserWrapper findDistinctByUser_Id(Long id);

    ChatUserWrapper findByAvailableAndId(Boolean available, Long id);

    List<ChatUserWrapper> findAllByAvailable(Boolean available);
}
