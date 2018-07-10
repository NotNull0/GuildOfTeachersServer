package com.mpls.web2.service;

import com.mpls.web2.model.Law;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface LawService {

    Law findOneAvailable(Long id);

    Law save(Law law);

    Law update(Law law);

    Boolean delete(Long id);

    Law findOne(Long id);

    List<Law> findAllAvailable();

    List<Law> findAll(Sort sort);

    List<Law> findAll();

    List<Law> findAllByContainerId(Long id);

    Law save(Law law, Long id);

}
