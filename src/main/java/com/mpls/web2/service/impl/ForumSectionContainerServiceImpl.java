package com.mpls.web2.service.impl;

import com.mpls.web2.model.ForumSectionContainer;
import com.mpls.web2.repository.ForumSectionContainerRepository;
import com.mpls.web2.repository.ForumSectionRepository;
import com.mpls.web2.service.ForumSectionContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class ForumSectionContainerServiceImpl implements ForumSectionContainerService {

    @Autowired
    private ForumSectionContainerRepository forumSectionContainerRepository;

    @Autowired
    private ForumSectionRepository forumSectionRepository;

    @Override
    public ForumSectionContainer findByHeader(String header) {
        checkString(header);
        return forumSectionContainerRepository.findByHeader(header);
    }

    @Override
    public ForumSectionContainer findOneAvailable(Long id) {
        checkId(id);
        return forumSectionContainerRepository.findByAvailableAndId(true, id);
    }

    @Override
    public ForumSectionContainer findOne(Long id) {
        checkId(id);
        return forumSectionContainerRepository.findOne(id);
    }

    @Override
    public ForumSectionContainer save(ForumSectionContainer forumSectionContainer) {
        checkSave(forumSectionContainer);
        forumSectionContainer.setAvailable(true);
        forumSectionContainer.setId(forumSectionContainerRepository.save(forumSectionContainer).getId());
        return forumSectionContainerRepository.save(forumSectionContainer.setSections(forumSectionContainer.getSections().stream().map(forumSection -> forumSectionRepository.save(forumSection.setContainer(forumSectionContainer))).collect(Collectors.toList())));
    }

    @Override
    public ForumSectionContainer update(ForumSectionContainer forumSectionContainer) {
        checkUpdate(forumSectionContainer.getId(), forumSectionRepository);
        return forumSectionContainerRepository.save(forumSectionContainer.setSections(forumSectionContainer.getSections().stream().map(forumSection ->
                forumSectionRepository.save(forumSection.setContainer(forumSectionContainer))).collect(Collectors.toList())));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            ForumSectionContainer forumSectionContainer = forumSectionContainerRepository.findOne(id);
            if (forumSectionContainer != null) {
                forumSectionContainerRepository.delete(forumSectionContainer);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public List<ForumSectionContainer> findAllAvailable() {
        return forumSectionContainerRepository.findAllByAvailable(true);
    }

    @Override
    public List<ForumSectionContainer> findAll() {
        return forumSectionContainerRepository.findAll();
    }

    @Override
    public ForumSectionContainer findBySectionId(Long id) {
        return forumSectionContainerRepository.findBySectionId(id);
    }
}
