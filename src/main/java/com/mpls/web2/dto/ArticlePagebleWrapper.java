package com.mpls.web2.dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ArticlePagebleWrapper {

    private List<ArticleShortDto> articles;
    private Integer currentPage;
    private Integer numberOfPages;
    private Integer numberOfItems;

    @Override
    public String toString() {
        return "ArticlePagebleWrapper{" +
                "articles=" + articles.stream().map(ArticleShortDto::getId).collect(toList()) +
                ", currentPage=" + (currentPage == null ? "null" : currentPage) +
                ", numberOfPages=" + (numberOfPages == null ? "null" : numberOfPages) +
                ", numberOfItems=" + (numberOfItems == null ? "null" : numberOfItems) +
                '}';
    }

    public List<ArticleShortDto> getArticles() {
        return articles;
    }

    public ArticlePagebleWrapper setArticles(List<ArticleShortDto> articles) {
        this.articles = articles;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public ArticlePagebleWrapper setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public ArticlePagebleWrapper setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public ArticlePagebleWrapper setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }
}
