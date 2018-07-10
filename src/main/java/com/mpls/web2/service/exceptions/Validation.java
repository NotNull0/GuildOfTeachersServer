package com.mpls.web2.service.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;

public class Validation {

    public static void checkId(Long id) {
        if (id == null || id < 0)
            throw new IdException("invalid id");
    }

    public static void checkString(String string) {
        if (string == null)
            throw new FindException(string + " must be not null");
    }

    //use when json is parameter instead of object in save method
    public static void checkJson(String json) {
        try {
            new ObjectMapper().readTree(String.valueOf(json));
        } catch (NullPointerException | IOException e) {
            throw new RuntimeException("json is null or empty");
        }
    }

    //aLong = obj.getId()
    public static <T> T checkUpdate(Long aLong, JpaRepository<T, Long> jpaRepository) {
        return checkObjectExistsById(aLong, jpaRepository, new UpdateException("there are no such object"));
    }

    public static <T> T checkObjectExistsById(Long aLong, JpaRepository<T, Long> jpaRepository, RuntimeException e) {
        checkId(aLong);
        T t;
        if ((t = jpaRepository.findOne(aLong)) == null) {
            throw e;
        }
        return t;
    }

    //use when object is parameter
    public static void checkSave(Object object) {
        if (object == null)
            throw new SaveException(object + " is null");
    }

}
