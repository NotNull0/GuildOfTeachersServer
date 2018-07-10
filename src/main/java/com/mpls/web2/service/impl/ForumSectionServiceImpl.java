package com.mpls.web2.service.impl;

import com.mpls.web2.model.ForumSection;
import com.mpls.web2.model.ForumSectionContainer;
import com.mpls.web2.repository.ForumSectionRepository;
import com.mpls.web2.service.ForumSectionContainerService;
import com.mpls.web2.service.ForumSectionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class ForumSectionServiceImpl implements ForumSectionService {

    private static final Logger logger = Logger.getLogger(ForumSectionServiceImpl.class);

    @Autowired
    private ForumSectionRepository forumSectionRepository;
    @Autowired
    private ForumSectionContainerService forumSectionContainerService;

    @Override
    public Page<ForumSection> findAll(Pageable pageable) {
        return forumSectionRepository.findAll(pageable);
    }

    @Override
    public ForumSection findByHeader(String header) {
        checkString(header);
        return forumSectionRepository.findByHeader(header);
    }

    @Override
    public ForumSection findByDescription(String description) {
        checkString(description);
        return forumSectionRepository.findByDescription(description);
    }

    @Override
    public ForumSection findOneAvailable(Long id) {
        checkId(id);
        return forumSectionRepository.findByAvailableAndId(true, id);
    }

    @Override
    public ForumSection findOne(Long id) {
        checkId(id);
        return forumSectionRepository.findOne(id);
    }

    @Override
    public ForumSection save(ForumSection forumSection) {
        checkSave(forumSection);
        forumSection.setAvailable(true);
        return forumSectionRepository.save(forumSection);
    }

    @Override
    public ForumSection save(ForumSection forumSection, Long id) {
        ForumSectionContainer forumSectionContainer = forumSectionContainerService.findOne(id);
        if (forumSectionContainer == null)
            throw new NullPointerException();
        forumSection = save(forumSection.setContainer(forumSectionContainer));
        logger.info(forumSection);
        return forumSection;
    }

    @Override
    public ForumSection update(ForumSection forumSection) {
        checkUpdate(forumSection.getId(), forumSectionRepository);
        return forumSectionRepository.save(forumSectionRepository.findOne(forumSection.getId())
                .setHeader(forumSection.getHeader())
                .setDescription(forumSection.getDescription())
                .setContainer(forumSectionContainerService.findOne(forumSection.getContainer().getId())));
    }

    @Override
    public ForumSection update(ForumSection forumSection, Long id) {
        checkUpdate(forumSection.getId(), forumSectionRepository);
        ForumSectionContainer forumSectionContainer = forumSectionContainerService.findOne(id);
        if (forumSectionContainer == null)
            throw new NullPointerException();
//        logger.info(forumSection);
        return forumSectionRepository.save(forumSectionRepository.findOne(forumSection.getId())
                .setContainer(forumSectionContainer)
                .setHeader(forumSection.getHeader())
                .setDescription(forumSection.getDescription()));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            ForumSection forumSection = forumSectionRepository.findOne(id);
            if (forumSection != null) {
                forumSectionRepository.delete(forumSection);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public List<ForumSection> findAllAvailable() {
        return forumSectionRepository.findAllByAvailable(true);
    }

    @Override
    public List<ForumSection> findAllByContainerId(Long id) {
        checkId(id);
        return forumSectionRepository.findAllByContainer_Id(id);
    }
}
