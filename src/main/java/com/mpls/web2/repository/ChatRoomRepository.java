package com.mpls.web2.repository;

import com.mpls.web2.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findByAvailableAndId(Boolean available, Long id);

    List<ChatRoom> findAllByAvailable(Boolean available);

    @Query(value = "SELECT * FROM chat_room INNER JOIN chat_room_user cru ON chat_room.id = cru.chat_room_id inner join chat_user_wrapper cuw on cuw.id=cru.user_id WHERE cuw.user_id=:id",
            nativeQuery = true)
//@Query("select ChatRoom from ChatRoom u where u.users ")
    List<ChatRoom> findAllByUserId(@Param("id") Long id);

}
