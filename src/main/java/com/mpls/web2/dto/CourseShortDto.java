package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.CourseCategory;
import com.mpls.web2.model.enums.CourseType;

@Dto
public class CourseShortDto<T extends CourseShortDto> {

    protected Long id;
    protected String header;
    protected String description;
    protected String image;
    protected Integer rating;
    protected CourseType type;
    protected Boolean available;
    protected CourseCategoryDto courseCategory;
    protected String link;
    protected Boolean canVote;
    protected Integer userRating;

    public Integer getUserRating() {
        return userRating;
    }

    public T setUserRating(Integer userRating) {
        this.userRating = userRating;
        return (T)this;
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public String getHeader() {
        return header;
    }

    public T setHeader(String header) {
        this.header = header;
        return (T) this;
    }

    public String getDescription() {
        return description;
    }

    public T setDescription(String description) {
        this.description = description;
        return (T) this;
    }

    public String getImage() {
        return image;
    }

    public T setImage(String image) {
        this.image = image;
        return (T) this;
    }


    public Integer getRating() {
        return rating;
    }

    public T setRating(Integer rating) {
        this.rating = rating;
        return (T)this;
    }

    public CourseType getType() {
        return type;
    }

    public T setType(CourseType type) {
        this.type = type;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    public CourseCategoryDto getCourseCategory() {
        return courseCategory;
    }

    public CourseShortDto<T> setCourseCategory(CourseCategoryDto courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    public String getLink() {
        return link;
    }

    public T setLink(String link) {
        this.link = link;
        return (T) this;
    }

    public Boolean getCanVote() {
        return canVote;
    }

    public CourseShortDto<T> setCanVote(Boolean canVote) {
        this.canVote = canVote;
        return this;
    }

    @Override
    public String toString() {
        return "CourseShortDto{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", type=" + type +
                ", available=" + available +
                ", courseCategory=" + courseCategory.getName() +
                ", link='" + link + '\'' +
                ", canVote=" + canVote +
                ", userRating=" + userRating +
                '}';
    }
}
