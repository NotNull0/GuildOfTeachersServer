package com.mpls.web2.repository;

import com.mpls.web2.model.LawContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawContainerRepository extends JpaRepository<LawContainer, Long> {

    LawContainer findByName(String name);

    List<LawContainer> findAllByAvailable(Boolean available);

    LawContainer findByAvailableAndId(Boolean available, Long id);


}
