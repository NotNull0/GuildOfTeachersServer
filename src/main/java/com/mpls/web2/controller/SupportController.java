package com.mpls.web2.controller;

import com.mpls.web2.dto.UserFullDto;
import com.mpls.web2.service.MailService;
import com.mpls.web2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

import static com.mpls.web2.dto.utils.builder.Builder.map;

@Controller
@RequestMapping("/support")
public class SupportController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @PostMapping("/send-message")
    private ResponseEntity<UserFullDto> support(@RequestParam String text, Principal principal) {
        return ResponseEntity.ok(map(mailService.support(userService.findByEmail(principal.getName()), text), UserFullDto.class));
    }

}
