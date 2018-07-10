package com.mpls.web2.service;

import com.mpls.web2.model.PlaceOfWork;

import java.util.List;

public interface PlaceOfWorkService {

    PlaceOfWork findByName(String name);

    PlaceOfWork save(PlaceOfWork placeOfWork);

    PlaceOfWork update(PlaceOfWork placeOfWork);

    Boolean delete(Long id);

    PlaceOfWork findOne(Long id);

    List<PlaceOfWork> findAll();

    void deleteAllByUsersNull();
}
