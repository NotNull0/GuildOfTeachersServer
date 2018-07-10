package com.mpls.web2.repository;

import com.mpls.web2.model.Specialization;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpecializationRepositoryTest {

    private Long id;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Before
    public void before(){
        id = entityManager.persist(new Specialization()).getId();
        System.err.println("----------------------before--------------------");
        System.err.println(specializationRepository.findOne(id));
    }

    @After
    public void after(){
        System.err.println("------------------------------------------");
        System.out.println(specializationRepository.findOne(id));
        System.err.println("------------------------------------------");
    }

    @Test
    public void deleteAllByUsersNull() {
        System.err.println("____________________________IN__TEST__________________________");
        System.err.println(specializationRepository.findOne(id));
        System.err.println("____________________________IN__TEST__________________________");
        specializationRepository.deleteAllByUsersNull();
        System.err.println("____________________________IN__TEST__________________________");
        System.err.println(specializationRepository.findOne(id));
        System.err.println("____________________________IN__TEST__________________________");
    }
}