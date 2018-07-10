package com.mpls.web2.repository;

import com.mpls.web2.model.PlaceOfWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceOfWorkRepository extends JpaRepository<PlaceOfWork, Long> {

    PlaceOfWork findByName(String name);

    List<PlaceOfWork> findAllByAvailable(Boolean available);

    PlaceOfWork findByAvailableAndId(Boolean available, Long id);

    //    @Modifying
//    @Transactional
//    @Query(value = "DELETE from place_of_work where id not in (" +
//            "select place_of_work.id from user u where " +
//            "place_of_work.id = u.place_of_work_id)", nativeQuery = true)
    @Query(value = "select * from place_of_work pow where pow.id not in (" +
            "select pow.id from user u where " +
            "pow.id = u.place_of_work_id)", nativeQuery = true)
    List<PlaceOfWork> findAllByUsersNull();

}
