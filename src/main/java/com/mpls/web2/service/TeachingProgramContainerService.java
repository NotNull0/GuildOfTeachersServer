package com.mpls.web2.service;

import com.mpls.web2.model.TeachingProgramContainer;

import java.util.List;

public interface TeachingProgramContainerService {

    TeachingProgramContainer findByName(String name);

    TeachingProgramContainer findOneAvailable(Long id);

    List<TeachingProgramContainer> findAllAvailable();

    TeachingProgramContainer save(TeachingProgramContainer teachingProgramContainer);

    TeachingProgramContainer update(TeachingProgramContainer teachingProgramContainer);

    Boolean delete(Long id);

    TeachingProgramContainer findOne(Long id);

    List<TeachingProgramContainer> findAll();

}
