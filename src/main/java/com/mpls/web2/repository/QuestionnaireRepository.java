package com.mpls.web2.repository;

import com.mpls.web2.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long>{

    Questionnaire findByAvailableAndId(Boolean available, Long id);

    List<Questionnaire> findAllByAvailable(Boolean available);

}
