package com.mpls.web2.service.impl;

import com.mpls.web2.model.TeachingProgramContainer;
import com.mpls.web2.repository.TeachingProgramContainerRepository;
import com.mpls.web2.service.TeachingProgramContainerService;
import com.mpls.web2.service.TeachingProgramService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class TeachingProgramContainerServiceImpl implements TeachingProgramContainerService {

    private static final Logger LOGGER = Logger.getLogger(TeachingProgramContainerServiceImpl.class);

    @Autowired
    private TeachingProgramContainerRepository teachingProgramContainerRepository;

    @Autowired
    private TeachingProgramService teachingProgramService;

    @Override
    public TeachingProgramContainer findByName(String name) {
        checkString(name);
        return teachingProgramContainerRepository.findByName(name);
    }

    @Override
    public TeachingProgramContainer findOneAvailable(Long id) {
        checkId(id);
        return teachingProgramContainerRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<TeachingProgramContainer> findAllAvailable() {
        return teachingProgramContainerRepository.findAllByAvailable(true);
    }


    @Override
    public TeachingProgramContainer save(TeachingProgramContainer teachingProgramContainer) {
        checkSave(teachingProgramContainer);
        teachingProgramContainer.setId(teachingProgramContainerRepository.save(teachingProgramContainer).getId());
        LOGGER.info("----------------------TP---------------------------");
//        teachingProgramContainer.getPrograms().forEach(teachingProgram -> LOGGER.info(teachingProgramService.save(teachingProgram.setContainer(teachingProgramContainer))));
        LOGGER.info("----------------------TP end---------------------------");
        return teachingProgramContainer;
    }

    @Override
    public TeachingProgramContainer update(TeachingProgramContainer teachingProgramContainer) {
        checkUpdate(teachingProgramContainer.getId(), teachingProgramContainerRepository);
        return teachingProgramContainerRepository.save(findOne(teachingProgramContainer.getId())
                .setName(teachingProgramContainer.getName()));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            TeachingProgramContainer teachingProgramContainer = teachingProgramContainerRepository.findOne(id);
            if (teachingProgramContainer != null) {
                teachingProgramContainerRepository.delete(teachingProgramContainer);
                return true;
            } else {
                return false;
            }
        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public TeachingProgramContainer findOne(Long id) {
        checkId(id);
        return teachingProgramContainerRepository.findOne(id);
    }

    @Override
    public List<TeachingProgramContainer> findAll() {
        return teachingProgramContainerRepository.findAll();
    }
}
