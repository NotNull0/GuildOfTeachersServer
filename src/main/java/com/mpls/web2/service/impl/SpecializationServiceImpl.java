package com.mpls.web2.service.impl;

import com.mpls.web2.model.Specialization;
import com.mpls.web2.repository.SpecializationRepository;
import com.mpls.web2.service.SpecializationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    private static final Logger LOGGER = Logger.getLogger(SpecializationServiceImpl.class);

    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public Specialization findByName(String name) {
        checkString(name);
        return specializationRepository.findByName(name);
    }

    @Override
    public Specialization findOneAvailable(Long id) {
        checkId(id);
        return specializationRepository.findByAvailableAndId(true, id);
    }

    @Override
    public Specialization save(Specialization specialization) {
        checkSave(specialization);
        return specializationRepository.save(specialization.setAvailable(true));
    }

    @Override
    public Specialization update(Specialization specialization) {
        checkUpdate(specialization.getId(), specializationRepository);
        return specializationRepository.save(findOne(specialization.getId())
                .setName(specialization.getName()));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            Specialization specialization = specializationRepository.findOne(id);
            if (specialization != null) {
                specializationRepository.delete(specialization);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public Specialization findOne(Long id) {
        checkId(id);
        return specializationRepository.findOne(id);
    }

    @Override
    public List<Specialization> findAllAvailable() {
        return specializationRepository.findAllByAvailable(true);
    }

    @Override
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    @Override
    public void deleteAllByUsersNull() {
        LOGGER.info("__________SPEC____________FIND_____ALL__________SPEC____________");
        LOGGER.info(specializationRepository.findAll());
        LOGGER.info("___________SPEC___________FIND_____ALL_____________SPEC_________");
        specializationRepository.deleteAllByUsersNull();
    }
}
