package com.mpls.web2.service;

import com.mpls.web2.dto.ArticlePagebleWrapper;
import com.mpls.web2.model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    Article findByHeader(String header);

    Article findByText(String text);

    Article findOneAvailable(Long id);

    Article findOne(Long id);

    Article save(String article, MultipartFile multipartFile);

    Article update(String article);

    Article updateImage(Long articleId, MultipartFile multipartFile);

    List<Article> findAll(Pageable pageable);

    ArticlePagebleWrapper findAllByAvailable(Pageable pageable);

    Boolean delete(Long id);

    List<Article> findAllAvailable();

    List<Article> findAllSort();

    List<Article> findTop5();

    List<Article> findAll();

}
