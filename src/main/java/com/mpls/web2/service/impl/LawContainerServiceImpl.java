package com.mpls.web2.service.impl;

import com.mpls.web2.model.LawContainer;
import com.mpls.web2.repository.LawContainerRepository;
import com.mpls.web2.service.LawContainerService;
import com.mpls.web2.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;
import static java.util.stream.Collectors.toList;

@Service
public class LawContainerServiceImpl implements LawContainerService {

    @Autowired
    private LawContainerRepository lawContainerRepository;

    @Autowired
    private LawService lawService;

    @Override
    public LawContainer findOneAvailable(Long id) {
        checkId(id);
        return lawContainerRepository.findByAvailableAndId(true, id);
    }

    @Override
    public LawContainer findByName(String name) {
        checkString(name);
        return lawContainerRepository.findByName(name);
    }

    @Override
    public LawContainer save(LawContainer lawContainer) {
        checkSave(lawContainer);
        lawContainer.setId(lawContainerRepository.save(lawContainer).getId());
        return lawContainerRepository.save(lawContainer.setLaws(lawContainer.getLaws().stream().map(law -> lawService.save(law.setContainer(lawContainer))).collect(toList())));
    }

    @Override
    public LawContainer update(LawContainer lawContainer) {
        checkUpdate(lawContainer.getId(),lawContainerRepository);
        return lawContainerRepository.save(lawContainer);
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            LawContainer lawContainer = lawContainerRepository.findOne(id);
            if (lawContainer != null) {
                lawContainerRepository.delete(lawContainer);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public LawContainer findOne(Long id) {
        checkId(id);
        return lawContainerRepository.findOne(id);
    }

    @Override
    public List<LawContainer> findAllAvailable() {
        return lawContainerRepository.findAllByAvailable(true);
    }

    @Override
    public List<LawContainer> findAll() {
        return lawContainerRepository.findAll();
    }
}
