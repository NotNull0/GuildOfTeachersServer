package com.mpls.web2.service.impl;

import com.mpls.web2.model.ForumTopic;
import com.mpls.web2.repository.ForumTopicRepository;
import com.mpls.web2.service.ForumSectionService;
import com.mpls.web2.service.ForumTopicService;
import com.mpls.web2.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class ForumTopicServiceImpl implements ForumTopicService {

    private static Logger LOGGER = Logger.getLogger(ForumTopicServiceImpl.class);

    @Autowired
    private ForumTopicRepository forumTopicRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ForumSectionService forumSectionService;


    @Override
    public List<ForumTopic> findAllByAvailableByForumSectionId(Pageable pageable, Long id) {
        return forumTopicRepository.findAllByAvailableAndForumSection_Id(true, id, pageable).getContent();
    }

    @Override
    public List<ForumTopic> findAll(Pageable pageable) {
        return forumTopicRepository.findAll(pageable).getContent();
    }

    @Override
    public ForumTopic findByHeader(String header) {
        checkString(header);
        return forumTopicRepository.findByHeader(header);
    }

    @Override
    public ForumTopic findOneAvailable(Long id) {
        checkId(id);
        return forumTopicRepository.findByAvailableAndId(true, id);
    }

    @Override
    public ForumTopic findOne(Long id) {
        return forumTopicRepository.findOne(id);
    }

    @Override
    public ForumTopic save(ForumTopic forumTopic) {
        checkSave(forumTopic);
        return forumTopicRepository.save(forumTopic.setDatetime(Timestamp.valueOf(LocalDateTime.now())));
    }

    @Override
    public ForumTopic save(ForumTopic forumTopic, Principal principal, Long id) {
        if (principal != null) {
            if (principal.getName() != null) {
                forumTopic.setAuthor(userService.findByEmail(principal.getName()));
                LOGGER.info("=============DANYA YA TEBE LUBLU ==========" + principal.getName() + "==========");
//                forumTopic.setAuthor(forumTopic.getAuthor());
//                LOGGER.info(forumTopic);
                forumTopic.setForumSection(forumSectionService.findOne(id));
                LOGGER.info("=============DANYA YA TEBE LUBLU ====================");
                return save(forumTopic);
            }
        }
        throw new NullPointerException();
    }

    @Override
    public ForumTopic update(ForumTopic forumTopic) {
        checkUpdate(forumTopic.getId(), forumTopicRepository);
        return forumTopicRepository.save(findOne(forumTopic.getId())
                .setHeader(forumTopic.getHeader())
                .setForumSection(forumSectionService.findOne(forumTopic.getForumSection().getId())));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            ForumTopic forumTopic = forumTopicRepository.findOne(id);
            if (forumTopic != null) {
                forumTopicRepository.delete(forumTopic);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public List<ForumTopic> findAllByAuthorId(Long id) {
        checkId(id);
        return forumTopicRepository.findAllByAuthor_Id(id);
    }

    @Override
    public List<ForumTopic> findAll(Sort sort) {
        return forumTopicRepository.findAll(sort);
    }

    @Override
    public List<ForumTopic> findAll() {
        return forumTopicRepository.findAll();
    }

    @Override
    public List<ForumTopic> findAllByForumSectionId(Long id) {
        checkId(id);
        return forumTopicRepository.findAllByForumSection_Id(id);
    }

    @Override
    public List<ForumTopic> findAllAvailable() {
        return forumTopicRepository.findAllByAvailable(true);
    }

    @Override
    public List<ForumTopic> findAllByAvailable(Integer pageNumber, Integer elementsNumber) {
        return forumTopicRepository.findAllByAvailable(new PageRequest(pageNumber, elementsNumber), true).getContent();
    }

    @Override
    public Integer countAllPages(Integer elementsOfPage) {
        return countAllByAvailable(true) / elementsOfPage + 1;
    }

    @Override
    public Integer countAllByAvailable(Boolean available) {
        return forumTopicRepository.countAllByAvailable(true);
    }
}
