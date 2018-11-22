package com.mpls.web2.controller;

import com.mpls.web2.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserConfirmController {

    Logger LOGGER = Logger.getLogger(UserConfirmController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/confirm/{uuid}")
    private String confirm(@PathVariable String uuid) {
        LOGGER.info("----------------------CONFIRM--------------------------");
        userService.confirmByUuid(uuid);
        LOGGER.info("-----------END-----------CONFIRM----------END----------------");
        return "redirect:http://localhost/#/sign-in";
    }

    @GetMapping("/confirm/key/{uuid}/{password}/{username}")
    private String confirmWithKey(@PathVariable String uuid, @PathVariable String password, @PathVariable String username) {
        LOGGER.info("----------------------CONFIRM--------------------------");
        userService.confirmByUuid(uuid);
        LOGGER.info("-----------END-----------CONFIRM----------END----------------");
        return "redirect:http://localhost/#/sign-in/" + password + "/" + username;
    }

}
