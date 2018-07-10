package com.mpls.web2.repository;

import com.mpls.web2.model.ForumSection;
import com.mpls.web2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumSectionRepository extends JpaRepository<ForumSection, Long> {

    ForumSection findByHeader(String header);

    ForumSection findByDescription(String description);

    List<ForumSection> findAllByContainer_Id(Long id);

    List<ForumSection> findAllByAvailable(Boolean available);

    ForumSection findByAvailableAndId(Boolean available, Long id);

    Page<ForumSection> findAll(Pageable pageable);


}
