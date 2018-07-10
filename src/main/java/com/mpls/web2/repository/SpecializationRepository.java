package com.mpls.web2.repository;

import com.mpls.web2.model.Specialization;
import com.mpls.web2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    Specialization findByName(String name);

    List<Specialization> findAllByAvailable(Boolean available);

    Specialization findByAvailableAndId(Boolean available, Long id);

    @Transactional
    void deleteAllByUsersNull();
}
