package com.mpls.web2.service.impl;

import com.mpls.web2.dto.QuestionnaireDto;
import com.mpls.web2.dto.UserGeneralInfoDto;
import com.mpls.web2.model.CourseCategory;
import com.mpls.web2.model.Questionnaire;
import com.mpls.web2.model.User;
import com.mpls.web2.repository.QuestionnaireRepository;
import com.mpls.web2.service.CourseCategoryService;
import com.mpls.web2.service.QuestionnaireService;
import com.mpls.web2.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static com.mpls.web2.service.exceptions.Validation.*;
import static java.util.stream.Collectors.toList;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private static final Logger LOGGER = Logger.getLogger(QuestionnaireServiceImpl.class);

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private CourseCategoryService courseCategoryService;

    @Autowired
    private UserService userService;

    @Override
    public Questionnaire save(Questionnaire questionnaire) {
        checkSave(questionnaire);
        questionnaireRepository.findAll().stream().forEach(questionnaire1 ->
                questionnaire1.setAvailable(false));
        questionnaire.setId(questionnaireRepository.save(questionnaire.setAvailable(true).setCanVote(true)).getId());
        questionnaire.getCourseCategories().stream().forEach(courseCategory -> {
            if (courseCategoryService.findOne(courseCategory.getId()) == null)
                throw new RuntimeException("There is no such category");
            else
                courseCategoryService.update(courseCategoryService.findOne(courseCategory.getId()).setQuestionnaire(questionnaire).setCount(0.0));
        });
        return countPercents(questionnaire);
    }

    @Override
    public Questionnaire update(Questionnaire questionnaire) {
        checkUpdate(questionnaire.getId(), questionnaireRepository);
        questionnaire.getCourseCategories().stream().map(courseCategory ->
                courseCategoryService.update(courseCategory)).collect(toList());
        return countPercents(questionnaireRepository.save(questionnaire));
    }

    @Override
    public List<Questionnaire> findAll(Principal principal) {
        if (principal == null || principal.getName() == null)
            return new ArrayList<>();
        return questionnaireRepository.findAll().stream().map(questionnaire -> {
            questionnaire.setCanVote(checkIfUserVoted(principal, questionnaire));
            return countPercents(questionnaire);
        }).collect(toList());
    }

    @Override
    public List<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    public Questionnaire findOne(Long id, Principal principal) {
        checkId(id);
        Questionnaire questionnaire = questionnaireRepository.findOne(id);
        return countPercents(questionnaire.setCanVote(checkIfUserVoted(principal, questionnaire)));
    }

    @Override
    public Boolean delete(Long id) {
        checkId(id);
        Questionnaire questionnaire = questionnaireRepository.findOne(id);
        if (questionnaire != null) {
            questionnaireRepository.delete(questionnaire);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Questionnaire findOneAvailable(Long id, Principal principal) {
        Questionnaire questionnaire = questionnaireRepository.findByAvailableAndId(true, id);
        return countPercents(questionnaire.setCanVote(checkIfUserVoted(principal, questionnaire)));
    }

    @Override
    public List<Questionnaire> findAllAvailable(Principal principal) {
        if (principal == null || principal.getName() == null)
            return new ArrayList<>();
        return questionnaireRepository.findAllByAvailable(true).stream().map(questionnaire ->
                countPercents(questionnaire.setCanVote(checkIfUserVoted(principal, questionnaire)))).collect(toList());
    }

    @Override
    public Questionnaire vote(Long questionnaireId, Long courseCategoryId, Principal principal) {
        Questionnaire questionnaire = questionnaireRepository.findOne(questionnaireId);
        CourseCategory courseCategory = courseCategoryService.findOne(courseCategoryId);
        List<User> users = questionnaire.getUsers();
        users.add(userService.findByEmail(principal.getName()));
        questionnaire.setUsers(users);
        courseCategoryService.update(courseCategory.setCount(courseCategory.getCount() + 1));
        questionnaire = questionnaireRepository.save(questionnaire);
        return countPercents(questionnaire.setCanVote(false));
    }

    private Boolean checkIfUserVoted(Principal principal, Questionnaire questionnaire) {
        if (principal == null || principal.getName() == null)
            return false;
        LOGGER.info(questionnaire.getUsers());
        User user = userService.findByEmail(principal.getName());
        for (User user1: questionnaire.getUsers()) {
            if(user1.equals(user))
                return false;
        }
        return true;
    }

    //Sending results of questionnaire in percents
    private Questionnaire countPercents(Questionnaire questionnaire) {
        Double sum = 0.0;
        for (CourseCategory category : questionnaire.getCourseCategories()) {
            sum += category.getCount();
        }
        Double finalSum = sum;
        return questionnaire.setCourseCategories(questionnaire.getCourseCategories().stream().map(courseCategory1 ->
                courseCategory1.getCount() == 0 ? courseCategory1 : courseCategory1.setCount(Double.parseDouble(new DecimalFormat("##.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
                        .format((courseCategory1.getCount() * 100) / finalSum)))
        ).collect(toList()));
    }
}
