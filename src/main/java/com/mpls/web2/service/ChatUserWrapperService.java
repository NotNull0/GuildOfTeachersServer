package com.mpls.web2.service;

import com.mpls.web2.model.ChatUserWrapper;

import java.util.List;

public interface ChatUserWrapperService {

    ChatUserWrapper findByUserId(Long id);

    ChatUserWrapper findOneAvailable(Long id);

    List<ChatUserWrapper> findAllAvailable();

    ChatUserWrapper findOne(Long id);

    ChatUserWrapper save(ChatUserWrapper chatUserWrapper);

    ChatUserWrapper update(ChatUserWrapper chatUserWrapper);

    Boolean delete(Long id);

    List<ChatUserWrapper> findAll();
}
