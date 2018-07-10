package com.mpls.web2.repository;

import com.mpls.web2.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Article findByHeader(String header);

    Article findByText(String text);

    Article findByAvailableAndId(Boolean available, Long id);

    List<Article> findAllByAvailable(Boolean available);

    Integer countAllByAvailable(Boolean available);

    Page<Article> findAllByAvailable(Boolean available, Pageable pageable);

    Page<Article> findAll(Pageable pageable);

    List<Article> findAll(Sort sort);

    List<Article> findAll();



}
