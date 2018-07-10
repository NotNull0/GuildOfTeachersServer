package com.mpls.web2.repository;

import com.mpls.web2.model.Law;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawRepository extends JpaRepository<Law, Long> {

    List<Law> findAllByContainer_Id(Long id);

    List<Law> findAll(Sort sort);

    List<Law> findAll();

    List<Law> findAllByAvailable(Boolean available);

    Law findByAvailableAndId(Boolean available, Long id);

}
