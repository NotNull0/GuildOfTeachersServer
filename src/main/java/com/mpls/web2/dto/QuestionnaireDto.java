package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Dto
public class QuestionnaireDto {

    private Long id;
    private List<CourseCategoryDto> courseCategories;
    private Boolean canVote;
    private Boolean available;

    private List<UserGeneralInfoDto> users;

    public Long getId() {
        return id;
    }

    public QuestionnaireDto setId(Long id) {
        this.id = id;
        return this;
    }

    public List<CourseCategoryDto> getCourseCategories() {
        return courseCategories;
    }

    public QuestionnaireDto setCourseCategories(List<CourseCategoryDto> courseCategories) {
        this.courseCategories = courseCategories;
        return this;
    }

    public Boolean getCanVote() {
        return canVote;
    }

    public QuestionnaireDto setCanVote(Boolean canVote) {
        this.canVote = canVote;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public QuestionnaireDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<UserGeneralInfoDto> getUsers() {
        return users;
    }

    public QuestionnaireDto setUsers(List<UserGeneralInfoDto> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return "QuestionnaireDto{" +
                "id=" + id +
                ", courseCategories=" + courseCategories.stream().map(CourseCategoryDto::getId).collect(toList()) +
                ", canVote=" + canVote +
                ", available=" + available +
                ", users=" + users.stream().map(UserGeneralInfoDto::getId).collect(toList())+
                '}';
    }
}
