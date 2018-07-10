package com.mpls.web2.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ForumMessageRepositoryTest {

    @Autowired
    ForumMessageRepository forumMessageRepository;

    @Test
    public void findAll(){
        forumMessageRepository.findAllByForumTopic_IdAndAvailable(new PageRequest(0,5),1L,true);
    }
}
