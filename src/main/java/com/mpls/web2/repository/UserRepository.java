package com.mpls.web2.repository;

import com.mpls.web2.model.User;
import com.mpls.web2.model.enums.Incumbency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email<>:email")
    List<User> findAllWithOutUser(@Param("email") String email);

    @Query("select u from User u where u.email<>:email")
    Page<User> findAllWithOutUser(@Param("email") String email, Pageable pageable);

    Integer countAllByAvailableAndEmailNot(Boolean available, String email);

    Page<User> findAllByAvailableAndEmailNot(Boolean available, String email, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByAvailable(Pageable pageable, Boolean available);

    List<User> findAllByName(String name);

    List<User> findAllByLastname(String lastname);

    List<User> findAllBySurname(String surname);

    User findByEmail(String email);

    User findByPhone(String phone);

    List<User> findAllByAvailable(Boolean available);

    User findByAvailableAndId(Boolean available, Long id);

    List<User> findAllByPlaceOfWork_Id(Long id);

    User findByUuid(String uuid);

    List<User> findAllByIncumbency(Incumbency incumbency);

    @Query("select count(u.id) from User u join " +
            "u.specializations s where " +
            "s.name like :name and " +
            "s.available = true and " +
            "u.available = true and " +
            "u.email <> :email")
    Integer countAllBySpecializationNameAndAvailableAndEmailNot(@Param("name") String name, @Param("email") String email);

    @Query("select u from User u join " +
            "u.specializations s where " +
            "s.name like :name and " +
            "s.available = true and " +
            "u.available = true and " +
            "u.email <> :email")
    Page<User> findAllBySpecializationNameAndAvailableAndEmailNot(@Param("name") String name, @Param("email") String email, Pageable pageable);

    @Query("select u from User u join " +
            "u.placeOfWork pow where " +
            "pow.name like :name and " +
            "u.available = true and " +
            "pow.available = true and " +
            "u.email <> :email")
    Page<User> findAllByPlaceOfWorkNameAndAvailableAndEmailNot(@Param("name") String name, @Param("email") String email, Pageable pageable);

    @Query("select count(u.id) from User u join " +
            "u.placeOfWork pow where " +
            "pow.name like :name and " +
            "u.available = true and " +
            "pow.available = true and " +
            "u.email <> :email")
    Integer countAllByPlaceOfWorkNameAndAvailableAndEmailNot(@Param("name") String name, @Param("email") String email);

    @Query("select u from User u join " +
            "u.specializations s join " +
            "u.placeOfWork pow where " +
            "s.name like :specializationName and " +
            "pow.name like :placeOfWorkName and " +
            "u.available = true and " +
            "pow.available = true and " +
            "s.available = true and " +
            "u.email <> :email")
    Page<User> findAllBySpecializationNameAndPlaceOfWorkNameAndAvailableAndEmailNot(@Param("specializationName") String specializationName,
                                                                                    @Param("placeOfWorkName") String placeOfWorkName,
                                                                                    @Param("email") String email,
                                                                                    Pageable pageable);

    @Query("select count(u.id) from User u join " +
            "u.specializations s join " +
            "u.placeOfWork pow where " +
            "s.name like :specializationName and " +
            "pow.name like :placeOfWorkName and " +
            "u.available = true and " +
            "pow.available = true and " +
            "s.available = true and " +
            "u.email <> :email")
    Integer countAllBySpecializationNameAndPlaceOfWorkNameAndAvailableAndEmailNot(@Param("specializationName") String specializationName,
                                                                                     @Param("placeOfWorkName") String placeOfWorkName,
                                                                                     @Param("email") String email);

    List<User> findAllByPerevireno(Boolean perevireno);
}
