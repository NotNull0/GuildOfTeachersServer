package com.mpls.web2.service;

import com.mpls.web2.model.User;

public interface MailService {

    User resetPassword(User user);

    User acceptRegistration(User user);

    User acceptRegistration(User user,String password);

    User support(User user, String text);
}
