package com.mpls.web2.service;

import com.mpls.web2.model.Questionnaire;

import java.security.Principal;
import java.util.List;

public interface QuestionnaireService {

    Questionnaire save(Questionnaire questionnaire);

    Questionnaire update(Questionnaire questionnaire);

    List<Questionnaire> findAll(Principal principal);

    List<Questionnaire> findAll();

    Questionnaire findOne(Long id, Principal principal);

    Boolean delete(Long id);

    Questionnaire findOneAvailable(Long id, Principal principal);

    List<Questionnaire> findAllAvailable(Principal principal);

    Questionnaire vote(Long questionnaireId, Long courseCategoryId, Principal principal);

}
