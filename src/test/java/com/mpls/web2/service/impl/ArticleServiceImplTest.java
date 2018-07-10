package com.mpls.web2.service.impl;

import com.mpls.web2.model.Article;
import com.mpls.web2.repository.ArticleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

//@Rollback
@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {

    private Long id;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        id = 1L;
        given(articleRepository.findByHeader("test")).willReturn(new Article().setHeader("test").setId(id));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findByHeader() {
        assertEquals(articleService.findByHeader("test").getId(),id);
    }
}