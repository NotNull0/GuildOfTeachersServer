package com.mpls.web2.repository;

import com.mpls.web2.model.ChatMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import sun.nio.cs.UTF_32;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatMessageRepositoryTest {

    private Long id;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @Test
    public void countAllByChatRoomIdAndHasBeenReadAndFromNot() {
        System.err.println(chatMessageRepository.countAllByChatRoom_IdAndHasBeenReadAndFromNot(1l, true, userRepository.findOne(1l)));
    }

    @Test
    public void saveEmoji() {
        chatMessageRepository.save(new ChatMessage().setText("\uD83D\uDE1C\uD83D\uDE00\uD83D\uDE0A\uD83D\uDE03"));
    }

    @Test
    public void saveTest() {
        chatMessageRepository.saveTest(new String("\uD83D\uDE1C\uD83D\uDE00\uD83D\uDE0A\uD83D\uDE03".getBytes(),new UTF_32()));
    }
}
