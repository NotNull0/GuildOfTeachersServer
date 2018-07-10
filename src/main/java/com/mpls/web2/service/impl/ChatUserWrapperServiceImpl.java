package com.mpls.web2.service.impl;

import com.mpls.web2.model.ChatUserWrapper;
import com.mpls.web2.repository.ChatUserWrapperRepository;
import com.mpls.web2.service.ChatUserWrapperService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.checkId;
import static com.mpls.web2.service.exceptions.Validation.checkSave;
import static com.mpls.web2.service.exceptions.Validation.checkUpdate;

@Service
public class ChatUserWrapperServiceImpl implements ChatUserWrapperService {

    private static final Logger LOGGER = Logger.getLogger(ChatUserWrapperServiceImpl.class);

    @Autowired
    private ChatUserWrapperRepository chatUserWrapperRepository;

    @Override
    public ChatUserWrapper findByUserId(Long id) {
        checkId(id);
        return chatUserWrapperRepository.findDistinctByUser_Id(id);
    }

    @Override
    public ChatUserWrapper findOneAvailable(Long id) {
        checkId(id);
        return chatUserWrapperRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<ChatUserWrapper> findAllAvailable() {
        return chatUserWrapperRepository.findAllByAvailable(true);
    }

    @Override
    public ChatUserWrapper findOne(Long id) {
        checkId(id);
        return chatUserWrapperRepository.findOne(id);
    }

    @Override
    public ChatUserWrapper save(ChatUserWrapper chatUserWrapper) {
        checkSave(chatUserWrapper);
        return chatUserWrapperRepository.save(chatUserWrapper.setDatetime(Timestamp.valueOf(LocalDateTime.now())));
    }

    @Override
    public ChatUserWrapper update(ChatUserWrapper chatUserWrapper) {
        return chatUserWrapperRepository.save(checkUpdate(chatUserWrapper.getId(),chatUserWrapperRepository));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            ChatUserWrapper chatUserWrapper = chatUserWrapperRepository.findOne(id);
            LOGGER.info("IN DELETE CHAT USER WRAPPER");
            if (chatUserWrapper != null) {
                chatUserWrapperRepository.delete(chatUserWrapper);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public List<ChatUserWrapper> findAll() {
        return chatUserWrapperRepository.findAll();
    }
}
