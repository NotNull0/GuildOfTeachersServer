package com.mpls.web2.service;

import com.mpls.web2.model.LawContainer;

import java.util.List;

public interface LawContainerService {

    LawContainer findOneAvailable(Long id);

    LawContainer findByName(String name);

    LawContainer save(LawContainer lawContainer);

    LawContainer update(LawContainer lawContainer);

    Boolean delete(Long id);

    LawContainer findOne(Long id);

    List<LawContainer> findAllAvailable();

    List<LawContainer> findAll();
}
