package com.mpls.web2.service;

import com.mpls.web2.dto.UserPageableWrapper;
import com.mpls.web2.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface UserService {

    User createFirstAdmin(String email);

    User save(String user, MultipartFile multipartFile);

    User update(String userJson);

    User updateImage(Long userId, MultipartFile multipartFile);

    User addFile(Long userId, MultipartFile multipartFile);

    List<User> findAll();

    void resetPassword(String email);

    User updatePassword(String password, String uuid);

    User findOne(Long id);

    List<User> findByName(String name);

    List<User> findByLastname(String lastname);

    List<User> findBySurname(String surname);

    User findByEmail(String email);

    User findByPhone(String phone);

    List<User> findAllAvailable();

    User findOneAvailable(Long id);

    List<User> findByPlaceOfWorkId(Long id);

    User findByUuid(String uuid);

    List<User> findAll(Principal principal);

    List<User> findAll(Principal principal, Pageable pageable);

    List<User> findAllByAvailable(Principal principal, Pageable pageable);

    User confirmByUuid(String uuid);

    List<User> findByIncumbency(Integer incumbency);

    Boolean delete(Long id);

    UserPageableWrapper findAllBySpecializationNameAndPlaceOfWorkNameAndAvailable(String specializationName,
                                                                                  String placeOfWorkName,
                                                                                  Pageable pageable,
                                                                                  Principal principal);

    List<User> findAllByPerevirenoNot();

    User pereviryty(User user);
}
