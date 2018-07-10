package com.mpls.web2.repository;

import com.mpls.web2.model.ForumSectionContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumSectionContainerRepository extends JpaRepository<ForumSectionContainer, Long> {

    ForumSectionContainer findByHeader(String header);

    List<ForumSectionContainer> findAllByAvailable(Boolean available);

    ForumSectionContainer findByAvailableAndId(Boolean available, Long id);

    @Query("select fsc from ForumSectionContainer fsc join fsc.sections s where s.id = :id")
    ForumSectionContainer findBySectionId(@Param("id") Long id);

}
