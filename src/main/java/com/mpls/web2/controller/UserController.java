package com.mpls.web2.controller;

import com.mpls.web2.dto.UserFullDto;
import com.mpls.web2.dto.UserGeneralInfoDto;
import com.mpls.web2.dto.UserPageableWrapper;
import com.mpls.web2.dto.UserShortDto;
import com.mpls.web2.model.User;
import com.mpls.web2.service.MailService;
import com.mpls.web2.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping("/create-first-admin/{email}")
    private ResponseEntity<UserGeneralInfoDto> createFirstAdmin(@PathVariable String email) {
        return ResponseEntity.ok(map(userService.createFirstAdmin(email), UserGeneralInfoDto.class));
    }

    @GetMapping
    private ResponseEntity<UserFullDto> getUser(Principal principal) {
        LOGGER.info(userService.findByEmail(principal.getName()));
        return ResponseEntity.ok(map(userService.findByEmail(principal.getName()), UserFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<UserShortDto> save(@RequestParam String user, @RequestParam(required = false) MultipartFile file) {
        User user1 = userService.save(user, file);
        try {
            return ResponseEntity.ok(map(user1, UserShortDto.class));
        } catch (Exception e) {
            userService.delete(user1.getId());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/update")
    private ResponseEntity<UserFullDto> update(@RequestParam String userJson, @RequestParam(required = false) MultipartFile file) {
        User user = userService.update(userJson);
        if (file != null)
            user = userService.updateImage(user.getId(), file);
        return ResponseEntity.ok(map(user, UserFullDto.class));
    }

    @GetMapping("/find-all-pageable/{page}/{count}")
    private ResponseEntity<List<UserShortDto>> findAllPageable(@PathVariable Integer page, @PathVariable Integer count, Principal principal) {
        return ResponseEntity.ok(userService.findAll(principal, new PageRequest(page, count)).stream().map(user -> map(user, UserShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-pageable-available/{page}/{count}")
    private ResponseEntity<List<UserShortDto>> findAllPageableAvailable(@PathVariable Integer page, @PathVariable Integer count, Principal principal) {
        return ResponseEntity.ok(userService.findAll(principal, new PageRequest(page, count)).stream().map(user -> map(user, UserShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<UserShortDto>> findAllPageableWithoutMe(Principal principal) {
        return ResponseEntity.ok(userService.findAll(principal).stream().map(user -> map(user, UserShortDto.class)).collect(toList()));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<UserFullDto> findOne(@PathVariable Long id) {
        User user = userService.findOne(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(user, UserFullDto.class));
    }

    @GetMapping("/find-by-name/{name}")
    private ResponseEntity<List<UserShortDto>> findByName(@PathVariable String name) {
        List<User> users = userService.findByName(name);
        if (users.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(users.stream().map(user -> map(user, UserFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-by-lastname/{lastname}")
    private ResponseEntity<List<UserShortDto>> findByLastname(@PathVariable String lastname) {
        List<User> users = userService.findByLastname(lastname);
        if (users.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(users.stream().map(user -> map(user, UserFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-by-surname/{surname}")
    private ResponseEntity<List<UserShortDto>> findBySurname(@PathVariable String surname) {
        List<User> users = userService.findBySurname(surname);
        if (users.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(users.stream().map(user -> map(user, UserShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-by-email/{email}")
    private ResponseEntity<UserFullDto> findByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(user, UserFullDto.class));
    }

    @GetMapping("/find-by-phone/{phone}")
    private ResponseEntity<UserFullDto> findByPhone(@PathVariable String phone) {
        User user = userService.findByPhone(phone);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(user, UserFullDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<UserShortDto>> findAllAvailable() {
        return ResponseEntity.ok(userService.findAllAvailable().stream().map(user -> map(user, UserShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<UserFullDto> findOneAvailable(@PathVariable Long id) {
        User user = userService.findOneAvailable(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(user, UserFullDto.class));
    }

    @GetMapping("/find-by-place-of-work/{id}")
    private ResponseEntity<List<UserShortDto>> findByPlaceOfWorkId(@PathVariable Long id) {
        List<User> users = userService.findByPlaceOfWorkId(id);
        if (users.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(users.stream().map(user -> map(user, UserShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-by-uuid/{uuid}")
    private ResponseEntity<UserFullDto> findByUuid(@PathVariable String uuid) {
        User user = userService.findByUuid(uuid);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(user, UserFullDto.class));
    }

    @GetMapping("/find-by-incumbency/{incumbencyNumber}")
    private ResponseEntity<List<UserShortDto>> findByIncumbency(@PathVariable Integer incumbencyNumber) {
        List<User> users = userService.findByIncumbency(incumbencyNumber);
        if (users.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(users.stream().map(user -> map(user, UserShortDto.class)).collect(toList()));
    }

    @PostMapping("/reset-password")
    private ResponseEntity resertPassword(@RequestParam String email) {
        userService.resetPassword(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-password")
    private ResponseEntity<UserFullDto> updatePassword(@RequestParam String newPassword, @RequestParam String uuid) {
        return ResponseEntity.ok(map(userService.updatePassword(newPassword, uuid), UserFullDto.class));
    }

    @GetMapping("/filter/{specializationName}/{placeOfWorkName}/{page}/{count}")
    private ResponseEntity<UserPageableWrapper> findAllBySpecializationAndPlaceOfWork(@PathVariable String specializationName,
                                                                                      @PathVariable String placeOfWorkName,
                                                                                      @PathVariable Integer page,
                                                                                      @PathVariable Integer count,
                                                                                      Principal principal) {
        return ResponseEntity.ok(userService.findAllBySpecializationNameAndPlaceOfWorkNameAndAvailable(
                specializationName, placeOfWorkName, new PageRequest(page, count), principal));
    }

    @GetMapping("/find-all-not-perevireno")
    private ResponseEntity<List<UserShortDto>> findAllNotPerevireno() {
        return ResponseEntity.ok(userService.findAllByPerevirenoNot().stream().map(user -> map(user, UserFullDto.class)).collect(toList()));
    }

    @PostMapping("/pereviryty")
    private ResponseEntity<UserShortDto> pereviryty(@RequestBody UserShortDto user) {
        return ResponseEntity.ok(map(userService.pereviryty(map(user, User.class)), UserShortDto.class));
    }
}
