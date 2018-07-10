package com.mpls.web2.service.impl;

import com.mpls.web2.dto.ArticlePagebleWrapper;
import com.mpls.web2.dto.ArticleShortDto;
import com.mpls.web2.model.Article;
import com.mpls.web2.repository.ArticleRepository;
import com.mpls.web2.service.ArticleService;
import com.mpls.web2.service.FileService;
import com.mpls.web2.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mpls.web2.config.json.mapper.JsonMapper.json;
import static com.mpls.web2.dto.utils.builder.Builder.map;
import static com.mpls.web2.service.exceptions.Validation.checkJson;
import static com.mpls.web2.service.exceptions.Validation.checkString;
import static java.util.stream.Collectors.toList;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final static Logger LOGGER = Logger.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Autowired
    private FileService fileService;

    @Override
    public ArticlePagebleWrapper findAllByAvailable(Pageable pageable) {
        return new ArticlePagebleWrapper().setArticles(articleRepository.findAllByAvailable(true, pageable).getContent()
                .stream().map(article -> map(article, ArticleShortDto.class)).collect(toList()))
                .setCurrentPage(pageable.getPageNumber())
                .setNumberOfItems(pageable.getPageSize())
                .setNumberOfPages((articleRepository.countAllByAvailable(true) / pageable.getPageSize()) + 1);
    }

    @Override
    public Article findByHeader(String header) {
        checkString(header);
        return articleRepository.findByHeader(header);
    }

    @Override
    public List<Article> findAll(Pageable pageable) {
        List<Article> articles = articleRepository.findAll(pageable).getContent();
        return articles;
    }

    @Override
    public Article findByText(String text) {
        return articleRepository.findByText(text);
    }

    @Override
    public Article findOneAvailable(Long id) {
        return articleRepository.findByAvailableAndId(true, id);
    }

    @Override
    public Article findOne(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public Article save(String articleJson, MultipartFile multipartFile) {
        checkJson(articleJson);
        Article article = json(articleJson, Article.class)
                .setDatetime(Timestamp.valueOf(LocalDateTime.now()))
                .setImage(fileBuilder.saveFile(multipartFile)).setAvailable(true);
        return articleRepository.save(article);
    }

    @Override
    public Article update(String articleJson) {
        checkJson(articleJson);
        Article article = json(articleJson, Article.class);
        Article articleToSave = findOne(article.getId());
        return articleRepository.save(articleToSave
                .setHeader(article.getHeader())
                .setText(article.getText())
                .setAvailable(article.getAvailable()));
    }

    @Override
    public Article updateImage(Long articleId, MultipartFile multipartFile) {
        Article article = findOne(articleId);
        try {
            return articleRepository.save(article.setImage(fileBuilder.saveFile(multipartFile)));
        } catch (Exception e) {
            return article;
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            Article article = articleRepository.findOne(id);
            if (article != null) {
                articleRepository.delete(article);
                return true;
            } else {
                return false;
            }
        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public List<Article> findAllAvailable() {
        LOGGER.info("------------------ARTICLE-------FIND-ALL-AVAILABLE----------------");
        List<Article> articles = articleRepository.findAllByAvailable(true);
        LOGGER.info("--------END----------ARTICLE-------FIND-ALL-AVAILABLE-------END---------");
        return articles;
    }

    @Override
    public List<Article> findAllSort() {
        return articleRepository.findAll(sortBy());
    }

    private Sort sortBy() {
//        for (Article article : articleRepository.findAll()) {
//            int count = article.getComments().size();
//        }
        return new Sort(Sort.Direction.ASC, "comments.size");
    }


    @Override
    public List<Article> findTop5() {
        List<Article> list = articleRepository.findAllByAvailable(true);
        List<Article> list2 = new ArrayList<>();
        if (list == null || list.size() < 5) {
            return list;
        } else {
            list.sort((p1, p2) -> Long.compare(p2.getComments().size(), p1.getComments().size()));
            for (int i = 0; i < 5; i++) {
                list2.add(list.get(i));
            }
            return list2;
        }
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
