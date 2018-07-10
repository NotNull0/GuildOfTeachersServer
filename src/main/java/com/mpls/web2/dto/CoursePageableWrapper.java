package com.mpls.web2.dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CoursePageableWrapper {

    private List<CourseShortDto> courses;
    private Integer currentPage;
    private Integer numberOfPages;
    private Integer numberOfItems;

    @Override
    public String toString() {
        return "CoursePageableWrapper{" +
                "courses=" + courses.stream().map(CourseShortDto::getId).collect(toList()) +
                ", currentPage=" + (currentPage == null ? "null" : currentPage) +
                ", numberOfPages=" + (numberOfPages == null ? "null" : numberOfPages) +
                ", numberOfItems=" + (numberOfItems == null ? "null" : numberOfItems) +
                '}';
    }

    public List<CourseShortDto> getCourses() {
        return courses;
    }

    public CoursePageableWrapper setCourses(List<CourseShortDto> courses) {
        this.courses = courses;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public CoursePageableWrapper setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public CoursePageableWrapper setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public CoursePageableWrapper setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }
}
