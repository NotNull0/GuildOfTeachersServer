package com.mpls.web2.service.impl;

import com.mpls.web2.config.json.mapper.JsonMapper;
import com.mpls.web2.dto.UserPageableWrapper;
import com.mpls.web2.dto.UserShortDto;
import com.mpls.web2.model.*;
import com.mpls.web2.model.enums.Incumbency;
import com.mpls.web2.model.enums.UserRole;
import com.mpls.web2.repository.UserRepository;
import com.mpls.web2.service.*;
import com.mpls.web2.service.utils.FileBuilder;
import com.mpls.web2.service.utils.GenerateUuid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static com.mpls.web2.service.exceptions.Validation.*;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenerateUuid generateUuid;

    @Autowired
    private PlaceOfWorkService placeOfWorkService;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private FileBuilder fileBuilder;

    @Autowired
    private MailService mailService;

    @Autowired
    private ChatUserWrapperService chatUserWrapperService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private CourseVoteService courseVoteService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ForumMessageService forumMessageService;

    @Override
    public User createFirstAdmin(String email) {
        if (userRepository.findAll().stream().anyMatch(user -> user.getRole().equals(UserRole.MODERATOR)))
            return null;
        User user = new User().setPassword(passwordEncoder.encode("moderator"))
                .setUuid(generateUuid.generateuuid())
                .setEmail("irynakor@gmail.com")
                .setRole(UserRole.MODERATOR)
                .setName("Iryna")
                .setLastname("Koropetska")
                .setSurname("")
                .setAvailable(true)
                .setImage("")
                .setIncumbency(Incumbency.DIRECTOR)
                .setInformation("")
                .setPerevireno(true)
                .setPhone("");
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllByAvailable(Principal principal, Pageable pageable) {
        if (principal != null && principal.getName() != null) {
            return userRepository.findAllWithOutUser(principal.getName(), pageable).getContent();
        } else {
            return userRepository.findAllByAvailable(pageable, true).getContent();
        }
    }

    @Override
    public void resetPassword(String email) {
        try {
            mailService.resetPassword(userRepository.save(userRepository.findByEmail(email).setUuid(generateUuid.generateuuid())));
        } catch (Exception e) {
            logger.info(email);
            logger.info(userRepository.findByEmail(email) == null);
        }
    }

    @Override
    public User updatePassword(String password, String uuid) {
        return userRepository.save(userRepository.findByUuid(uuid).setPassword(passwordEncoder.encode(password)));
    }

    @Override
    public User save(String userJson, MultipartFile multipartFile) {
        checkJson(userJson);
        User user = JsonMapper.json(userJson, User.class);
//        logger.info("_---------------------------");
//        logger.info(user.getEmail());
//        logger.info("_---------------------------");
        if (userRepository.findByEmail(user.getEmail()) != null) {
//            logger.info("_---------------------------");
//            logger.info("User already exists");
//            logger.info("_---------------------------");
            throw new RuntimeException("User already exists");
        }
        String password = user.getPassword();
        if (multipartFile != null)
            user.setImage(fileBuilder.saveFile(multipartFile));
        User savedUser = mailService.acceptRegistration(userRepository.save(generateUuid.generateuuid(user
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRole(UserRole.USER)
                .setPlaceOfWork(setPlaceOfWork(user))
                .setSpecializations(setSpecializations(user))
                .setFacebookLink(user.getFacebookLink())
                .setAvailable(false))
                .setPerevireno(false)), password);
        chatUserWrapperService.save(new ChatUserWrapper().setUser(savedUser).setAvailable(true));
        return savedUser;
    }

    @Override
    public User update(String userJson) {
        checkJson(userJson);
        User user = JsonMapper.json(userJson, User.class);
        logger.info(user);
        User userToSave = userRepository.save(userRepository.findOne(user.getId()).setEmail(user.getEmail())
                .setInformation(user.getInformation())
                .setLastname(user.getLastname()).setName(user.getName()).setSurname(user.getSurname())
                .setSpecializations(setSpecializations(user))
                .setPhone(user.getPhone())
                .setIncumbency(user.getIncumbency())
                .setPlaceOfWork(setPlaceOfWork(user))
                .setFacebookLink(user.getFacebookLink())
                .setPassword(checkUpdate(user.getId(), userRepository).getPassword()));
        deleteSpecializationsAndPlaceOfWorkWhereUserNull();
        return userToSave;
    }

    @Override
    public User updateImage(Long userId, MultipartFile multipartFile) {
        User user = userRepository.findOne(userId);
        try {
//            fileService.delete(fileService.findByPath(user.getImage()).getId());
            return userRepository.save(user.setImage(fileBuilder.saveFile(multipartFile)));
        } catch (Exception e) {
            return userRepository.findOne(userId);
        }
    }

    //todo
    @Override
    public User addFile(Long userId, MultipartFile multipartFile) {
        List<File> userFiles = userRepository.findOne(userId).getFiles();
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User findOne(Long id) {
        checkId(id);
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findByName(String name) {
        checkString(name);
        return userRepository.findAllByName(name);
    }

    @Override
    public List<User> findByLastname(String lastname) {
        checkString(lastname);

        return userRepository.findAllByLastname(lastname);
    }

    @Override
    public List<User> findBySurname(String surname) {
        checkString(surname);
        return userRepository.findAllBySurname(surname);
    }

    @Override
    public User findByEmail(String email) {
        checkString(email);
//        logger.info("find by email [ " + email + " ]");
//        logger.info(userRepository.findByEmail(email));
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        checkString(phone);
        return userRepository.findByPhone(phone);
    }

    @Override
    public List<User> findAllAvailable() {
        return userRepository.findAllByAvailable(true);
    }

    @Override
    public User findOneAvailable(Long id) {
        checkId(id);
        return userRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<User> findByPlaceOfWorkId(Long id) {
        checkId(id);
        return userRepository.findAllByPlaceOfWork_Id(id);
    }

    @Override
    public User findByUuid(String uuid) {
        checkString(uuid);
        return userRepository.findByUuid(uuid);
    }

    @Override
    public List<User> findAll(Principal principal) {
        if (principal != null && principal.getName() != null) {
            return userRepository.findAllWithOutUser(principal.getName());
        } else {
            return userRepository.findAll();
        }
    }

    @Override
    public List<User> findAll(Principal principal, Pageable pageable) {
        if (principal != null && principal.getName() != null) {
            return userRepository.findAllWithOutUser(principal.getName(), pageable).getContent();
        } else {
            return userRepository.findAll(pageable).getContent();
        }
    }

    @Override
    public User confirmByUuid(String uuid) {
        User user = null;
        if (ofNullable(user = findByUuid(uuid)).isPresent())
            return userRepository.save(user.setAvailable(true));
        return null;
    }

    @Override
    public List<User> findByIncumbency(Integer incumbencyNumber) {
        return userRepository.findAllByIncumbency(Incumbency.values()[incumbencyNumber]);
    }

    @Override
    public Boolean delete(Long id) {
        User user = findOne(id);
        try {
            final String basePath = "file:/" + System.getProperty("catalina.home");
            if (user.getFiles() != null)
                user.getFiles().forEach(file -> {
                    fileService.delete(file.getId());
                });
            if (new java.io.File(basePath + user.getImage()).delete()) {
                logger.info("delete Image user:[" + user.getId() + "]");
            } else {
                logger.info("error delete Image user:[" + user.getId() + "]");
            }
            deleteForumMessagesByUserId(id);
            courseVoteService.delete(courseVoteService.findAllByUserId(id));
            deleteCommentsByUserId(id);
            deleteUserFromQuestionnaires(user);
            chatUserWrapperService.delete(chatUserWrapperService.findByUserId(id).getId());
            userRepository.delete(user.getId());
            deleteSpecializationsAndPlaceOfWorkWhereUserNull();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("______IN__CATCH__ERROR_____");
//            userRepository.delete(user.getId());
            return false;
        }
    }

    @Override
    public UserPageableWrapper findAllBySpecializationNameAndPlaceOfWorkNameAndAvailable(String specializationName,
                                                                                         String placeOfWorkName,
                                                                                         Pageable pageable,
                                                                                         Principal principal) {
        UserPageableWrapper wrapper = new UserPageableWrapper()
                .setCurrentPage(pageable.getPageNumber())
                .setNumberOfItems(pageable.getPageSize());
        List<User> users;
        Integer size = 0;

        if (specializationName.equals("empty") && !placeOfWorkName.equals("empty")) {
            size = userRepository.countAllByPlaceOfWorkNameAndAvailableAndEmailNot("%" + placeOfWorkName + "%", principal.getName());
            users = userRepository.findAllByPlaceOfWorkNameAndAvailableAndEmailNot(
                    "%" + placeOfWorkName + "%",
                    principal.getName(), pageable).getContent();
            return wrapper.setUsers(users.stream().map(user -> map(user, UserShortDto.class)).collect(toList()))
                    .setNumberOfPages((size / pageable.getPageSize()) + 1);
        }
        if (!specializationName.equals("empty") && placeOfWorkName.equals("empty")) {
            size = userRepository.countAllBySpecializationNameAndAvailableAndEmailNot("%" + specializationName + "%", principal.getName());
            users = userRepository.findAllBySpecializationNameAndAvailableAndEmailNot("%" + specializationName + "%",
                    principal.getName(), pageable).getContent();
            return wrapper.setUsers(users.stream().map(user -> map(user, UserShortDto.class)).collect(toList()))
                    .setNumberOfPages((size / pageable.getPageSize()) + 1);
        }
        if (!specializationName.equals("empty") && !placeOfWorkName.equals("empty")) {
            size = userRepository.countAllBySpecializationNameAndPlaceOfWorkNameAndAvailableAndEmailNot("%" + specializationName + "%", "%" + placeOfWorkName + "%",
                    principal.getName());
            users = userRepository.findAllBySpecializationNameAndPlaceOfWorkNameAndAvailableAndEmailNot(
                    "%" + specializationName + "%", "%" + placeOfWorkName + "%",
                    principal.getName(), pageable).getContent();
            return wrapper.setUsers(users.stream().map(user -> map(user, UserShortDto.class)).collect(toList()))
                    .setNumberOfPages((size / pageable.getPageSize()) + 1);
        }
        size = userRepository.countAllByAvailableAndEmailNot(true, principal.getName());
        users = userRepository.findAllByAvailableAndEmailNot(true, principal.getName(), pageable).getContent();
        return wrapper.setUsers(users.stream().map(user -> map(user, UserShortDto.class)).collect(toList()))
                .setNumberOfPages((size / pageable.getPageSize() + 1));
    }

    @Override
    public List<User> findAllByPerevirenoNot() {
        return userRepository.findAllByPerevireno(false);
    }

    @Override
    public User pereviryty(User user) {
        checkUpdate(user.getId(), userRepository);
        User userToSave = userRepository.findOne(user.getId());
        userToSave = userRepository.save(userToSave.setPerevireno(true)
                .setPlaceOfWork(setPlaceOfWork(user))
                .setSpecializations(setSpecializations(user))
                .setRole(user.getRole())
                .setIncumbency(user.getIncumbency()));
        deleteSpecializationsAndPlaceOfWorkWhereUserNull();
        return userToSave;
    }

    private void deleteUserFromQuestionnaires(User user) {
        questionnaireService.findAll().stream().forEach(questionnaire -> {
            List<User> users = questionnaire.getUsers();
            if (users.contains(user)) {
                users.remove(user);
                questionnaire.setUsers(users);
            }
        });
    }

    private void deleteCommentsByUserId(Long id) {
        commentService.delete(commentService.findAllByFromId(id));
    }

    private void deleteForumMessagesByUserId(Long id) {
        forumMessageService.delete(forumMessageService.findAllByFromId(id));
    }

    private List<Specialization> setSpecializations(User user) {
//        user.getSpecializations().stream().filter(specialization -> !specializationService.findAllAvailable().stream().map(Specialization::getName).collect(toList()).contains(specialization.getName())).forEach(specialization -> {
//            user.getSpecializations().get(user.getSpecializations().indexOf(specialization)).setId(specializationService.save(specialization).getId());
//        });
//        user.getSpecializations().stream().filter(specialization -> specializationService.findAllAvailable().stream().map(Specialization::getName).collect(toList()).contains(specialization.getName())).forEach(specialization -> {
//            user.getSpecializations().get(user.getSpecializations().indexOf(specialization)).setId(specializationService.findByName(specialization.getName()).getId());
//        });

        return user.getSpecializations().stream().map(specialization -> {
            if (specializationService.findByName(specialization.getName()) != null) {
                logger.info("find by name" + specialization.getName());
                return specializationService.findByName(specialization.getName());
            } else if (specialization.getId() != null && specializationService.findOne(specialization.getId()) != null) {
                logger.info("find by id" + specialization.getId());
                return specializationService.update(specialization);
            } else {
                logger.info("save" + specialization);
                return specializationService.save(specialization);
            }
        }).collect(toList());
    }

    private PlaceOfWork setPlaceOfWork(User user) {
        PlaceOfWork placeOfWork = placeOfWorkService.findByName(user.getPlaceOfWork().getName());
        if (placeOfWork != null)
            return placeOfWork;
        else
            return placeOfWorkService.save(user.getPlaceOfWork());
    }

    private void deleteSpecializationsAndPlaceOfWorkWhereUserNull() {
        logger.info("before spec");
        specializationService.deleteAllByUsersNull();
        logger.info("before placeOfWork");
        placeOfWorkService.deleteAllByUsersNull();
    }


}
