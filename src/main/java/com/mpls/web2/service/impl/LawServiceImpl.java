package com.mpls.web2.service.impl;

import com.mpls.web2.model.Law;
import com.mpls.web2.model.LawContainer;
import com.mpls.web2.repository.LawRepository;
import com.mpls.web2.service.LawContainerService;
import com.mpls.web2.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class LawServiceImpl implements LawService {

    @Autowired
    private LawRepository lawRepository;

    @Autowired
    private LawContainerService lawContainerService;

    @Override
    public Law save(Law law, Long id) {
        LawContainer lawContainer = lawContainerService.findOne(id);
        if (lawContainer == null) {
            throw new NullPointerException();
        }
        lawRepository.save(law.setContainer(lawContainer).setDatetime(Timestamp.valueOf(LocalDateTime.now())));
        return law;
    }

    @Override
    public Law findOneAvailable(Long id) {
        checkId(id);
        return lawRepository.findByAvailableAndId(true, id);
    }

    @Override
    public Law save(Law law) {
        checkSave(law);
        return lawRepository.save(law.setDatetime(Timestamp.valueOf(LocalDateTime.now())));
    }

    @Override
    public Law update(Law law) {
        checkUpdate(law.getId(), lawRepository);
        return lawRepository.save(lawRepository.findOne(law.getId())
                .setName(law.getName())
                .setPath(law.getPath())
                .setAvailable(law.getAvailable())
                .setContainer(law.getContainer())
        );
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            Law law1 = lawRepository.findOne(id);
            if (law1 != null) {
                lawRepository.delete(law1);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public Law findOne(Long id) {
        checkId(id);
        return lawRepository.findOne(id);
    }

    @Override
    public List<Law> findAllAvailable() {
        return lawRepository.findAllByAvailable(true);
    }

    @Override
    public List<Law> findAll(Sort sort) {
        return lawRepository.findAll(sort);
    }

    @Override
    public List<Law> findAll() {
        return lawRepository.findAll();
    }

    @Override
    public List<Law> findAllByContainerId(Long id) {
        checkId(id);
        return lawRepository.findAllByContainer_Id(id);
    }
}
