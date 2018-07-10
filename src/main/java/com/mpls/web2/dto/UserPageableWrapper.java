package com.mpls.web2.dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserPageableWrapper {

    private List<UserShortDto> users;
    private Integer currentPage;
    private Integer numberOfPages;
    private Integer numberOfItems;

    public List<UserShortDto> getUsers() {
        return users;
    }

    public UserPageableWrapper setUsers(List<UserShortDto> users) {
        this.users = users;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public UserPageableWrapper setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public UserPageableWrapper setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public UserPageableWrapper setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }

    @Override
    public String toString() {
        return "UserPageableWrapper{" +
                "users=" + users.stream().map(UserShortDto::getId).collect(toList()) +
                ", currentPage=" + (currentPage == null ? "null" : currentPage) +
                ", numberOfPages=" + (numberOfPages == null ? "null" : numberOfPages) +
                ", numberOfItems=" + (numberOfItems == null ? "null" : numberOfItems) +
                '}';
    }
}
