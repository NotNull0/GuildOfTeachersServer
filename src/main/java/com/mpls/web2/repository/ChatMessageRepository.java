package com.mpls.web2.repository;

import com.mpls.web2.model.ChatMessage;
import com.mpls.web2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    ChatMessage findByAvailableAndId(Boolean available, Long id);

    List<ChatMessage> findAllByFrom_Id(Long id);

    List<ChatMessage> findAllByAvailable(Boolean available);

    List<ChatMessage> findAll(Sort sort);

    List<ChatMessage> findAll();

    List<ChatMessage> findAllByFrom_IdAndHasBeenRead(Long id, Boolean read);

    Integer countAllByChatRoom_IdAndHasBeenReadAndFromNot(Long chatRoom_id, Boolean hasBeenRead, User from);

    Integer countAllByHasBeenReadAndFromNot(Boolean hasBeenRead, User from);

    List<ChatMessage> findAllByChatRoom_Id(Long chatRoom_id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO chat_message (text) VALUES (?1)",nativeQuery = true)
    void saveTest(@Param("text") String text);
}
