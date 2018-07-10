package com.mpls.web2.repository;

import com.mpls.web2.model.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest {

    private Long id;
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @Before
    public void before() {
        id = entityManager.persist(new Article().setHeader("test")).getId();
        entityManager.flush();
    }
    @After
    public void after() {
    }

    /**
     * тестування пошуку
     */
    @Test
    public void findByHeader() {
        assertEquals(articleRepository.findByHeader("test").getId(), id);
    }

    /**
     * тестування нагрузки пошуку повторення запиту 10
     */
    @Repeat(10)
    @Test
    public void findByHeaderRepeat() {
        assertEquals(articleRepository.findByHeader("test").getId(), id);
    }
}