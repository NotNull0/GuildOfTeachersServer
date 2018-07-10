package com.mpls.web2.service.utils;


import com.mpls.web2.model.User;
import com.mpls.web2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class GenerateUuid {

    @Autowired
    private UserRepository userRepository;

    public User generateuuid(User user) {
        return user.setUuid(generateuuid());
    }

    public String generateuuid() {

        String uuid = null;

        do {
            uuid = UUID.randomUUID().toString();
        } while (userRepository.findByUuid(uuid) != null);
        return uuid;
    }


}
