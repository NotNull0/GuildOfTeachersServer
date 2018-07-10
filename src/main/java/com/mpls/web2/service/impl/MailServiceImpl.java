package com.mpls.web2.service.impl;

import com.mpls.web2.model.User;
import com.mpls.web2.service.MailService;
import com.mpls.web2.service.utils.MailContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {


    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailContentBuilder mailContentBuilder;


    //title - шапка сообщения
    //mail - кому
    //template - назва темлету що надсилається
    //map
    private String send(String mail, String title, String template, Map<String, Object> model) {
        String text = mailContentBuilder.getFreeMarkerTemplateContent(template, model);
        mailSender.send(mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            messageHelper.setTo(mail);
            messageHelper.setSubject(title);
            messageHelper.setText(text, true);
        });
        return text;
    }


    @Override
    public User resetPassword(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", "http://teachers.org.ua/#/restore-password/change/" + user.getUuid());
        send(user.getEmail(), "Відновлення паролю", "resPass.html", map);
        return user;
    }

    @Override
    public User acceptRegistration(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", user.getUuid());
        send(user.getEmail(), "Підтвердження", "acceptUser.html", map);
        return user;
    }

    @Override
    public User acceptRegistration(User user, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", user.getUuid());
        map.put("username", user.getUsername());
        map.put("password", password);
        send(user.getEmail(), "Підтвердження", "acceptUserWithKey.html", map);

        return user;
    }

    @Override
    public User support(User user, String text) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("lastname", user.getLastname());
        map.put("surname", user.getSurname());
        map.put("text", text);
        map.put("datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy о hh:mm:ss")));
        send("support@m-pls.com", "Підтримка Гільдії Вчителів", "supportAdmin.html", map);
        send(user.getEmail(), "Підтримка Гільдії Вчителів", "supportUser.html", new HashMap<>());
        return user;
    }
}
