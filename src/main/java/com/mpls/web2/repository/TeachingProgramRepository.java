package com.mpls.web2.repository;

import com.mpls.web2.model.TeachingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingProgramRepository extends JpaRepository<TeachingProgram, Long> {

    TeachingProgram findByName(String name);

    List<TeachingProgram> findAllByContainer_Id(Long id);

    List<TeachingProgram> findAllByAvailable(Boolean available);

    TeachingProgram findByAvailableAndId(Boolean available, Long id);

}
