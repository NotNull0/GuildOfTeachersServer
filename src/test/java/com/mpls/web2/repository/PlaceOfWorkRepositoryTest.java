package com.mpls.web2.repository;

import com.mpls.web2.service.PlaceOfWorkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlaceOfWorkRepositoryTest {

    @Autowired
    private PlaceOfWorkRepository placeOfWorkRepository;

    @Test
    public void deleteAllByUsersNull() {
        placeOfWorkRepository.findAllByUsersNull();
    }
}