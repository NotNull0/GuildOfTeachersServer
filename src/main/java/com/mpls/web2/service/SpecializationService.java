package com.mpls.web2.service;

import com.mpls.web2.model.Specialization;

import java.util.List;

public interface SpecializationService {

    Specialization findByName(String name);

    Specialization findOneAvailable(Long id);

    Specialization save(Specialization specialization);

    Specialization update(Specialization specialization);

    Boolean delete(Long id);

    Specialization findOne(Long id);

    List<Specialization> findAllAvailable();

    List<Specialization> findAll();

    void deleteAllByUsersNull();

}
