package com.mpls.web2.repository;

import com.mpls.web2.model.TeachingProgramContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingProgramContainerRepository extends JpaRepository<TeachingProgramContainer, Long> {

    TeachingProgramContainer findByName(String name);

    List<TeachingProgramContainer> findAllByAvailable(Boolean available);

    TeachingProgramContainer findByAvailableAndId(Boolean available, Long id);




}
