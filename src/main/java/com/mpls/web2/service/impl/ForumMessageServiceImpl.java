package com.mpls.web2.service.impl;

import com.mpls.web2.model.ForumMessage;
import com.mpls.web2.model.utils.MessageDateComparator;
import com.mpls.web2.repository.ForumMessageRepository;
import com.mpls.web2.service.ForumMessageService;
import com.mpls.web2.service.ForumTopicService;
import com.mpls.web2.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class ForumMessageServiceImpl implements ForumMessageService {

    private static final Logger logger = Logger.getLogger(ForumMessageServiceImpl.class);

    @Autowired
    private ForumMessageRepository forumMessageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ForumTopicService forumTopicService;


    @Override
    public List<ForumMessage> findAllByForumTopicId(Pageable pageable, Long id) {
        List<ForumMessage> messages = forumMessageRepository.findAllByForumTopic_IdAndAvailable(pageable, id, true).getContent().stream().map(forumMessage -> forumMessage.setFiles(new ArrayList<>())).collect(Collectors.toList());
        try {
            if (messages != null && messages.size() > 0) {
                logger.info("\n----------------------------------\n");
                logger.info(messages.size());
                logger.info("\n----------------------------------\n");
                messages.sort(new MessageDateComparator());
            }
        } catch (Exception e) {
            logger.info("\n----------------------------------\n");
            logger.info("pezda");
            logger.info("\n----------------------------------\n");
        }
        return messages;
    }

    @Override
    public List<ForumMessage> findAll(Pageable pageable) {
        return forumMessageRepository.findAll(pageable).getContent();
    }

    @Override
    public List<ForumMessage> findAllByFromId(Long id) {
        checkId(id);
        return forumMessageRepository.findAllByFrom_Id(id);
    }

    @Override
    public ForumMessage findOneAvailable(Long id) {
        checkId(id);
        return forumMessageRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<ForumMessage> findAllAvailable() {
        return forumMessageRepository.findAllByAvailable(true);
    }

    @Override
    public ForumMessage findOne(Long id) {
        checkId(id);
        return forumMessageRepository.findOne(id);
    }

    @Override
    public ForumMessage save(ForumMessage forumMessage) {
        checkSave(forumMessage);
        return forumMessageRepository.save(forumMessage.setDatetime(Timestamp.valueOf(LocalDateTime.now())));
    }

    @Override
    public ForumMessage save(ForumMessage forumMessage, Principal principal) {
        return save(forumMessage.setFrom(userService.findByEmail(principal.getName())).setAvailable(true));
    }

    @Override
    public ForumMessage save(ForumMessage forumMessage, Principal principal, Long id) {
        return save(forumMessage.setForumTopic(forumTopicService.findOne(id)), principal);
    }

    @Override
    public ForumMessage update(ForumMessage forumMessage) {

        return forumMessageRepository.save(checkUpdate(forumMessage.getId(), forumMessageRepository));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            ForumMessage forumMessage = forumMessageRepository.findOne(id);
            if (forumMessage != null) {
                forumMessageRepository.delete(forumMessage);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public Boolean delete(Iterable<? extends ForumMessage> forumMessages) {
        try {
            forumMessageRepository.delete(forumMessages);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ForumMessage> findAll() {
        return forumMessageRepository.findAll();
    }
}
