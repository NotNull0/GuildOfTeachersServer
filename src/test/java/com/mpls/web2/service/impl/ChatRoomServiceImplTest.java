package com.mpls.web2.service.impl;

import com.mpls.web2.model.ChatMessage;
import com.mpls.web2.service.ChatMessageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import sun.security.acl.PrincipalImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChatRoomServiceImplTest {

    private static final Long CHAT_ROOM_ID = 1L;

    @InjectMocks
    private ChatRoomServiceImpl chatRoomService;

    @Mock
    private ChatMessageService chatMessageService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findOneWithMessagesPageable() {
        when(chatMessageService.countAllUnreadByChatRoom(CHAT_ROOM_ID, new PrincipalImpl("danik"))).thenReturn(5);
        assertEquals(new Integer(5), chatMessageService.countAllUnreadByChatRoom(CHAT_ROOM_ID, new PrincipalImpl("danik")));
    }

    @Test
    public void findAllByUserId() {
    }

    @Test
    public void createOrFindChatRoom() {
    }
}