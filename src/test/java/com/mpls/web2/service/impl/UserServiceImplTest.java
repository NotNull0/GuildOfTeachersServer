package com.mpls.web2.service.impl;

import com.mpls.web2.model.PlaceOfWork;
import com.mpls.web2.model.Specialization;
import com.mpls.web2.model.User;
import com.mpls.web2.repository.UserRepository;
import com.mpls.web2.service.PlaceOfWorkService;
import com.mpls.web2.service.SpecializationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceImplTest {

    private static final Long USER_ID = 15L;
    private static final PlaceOfWork PLACE_OF_WORK = new PlaceOfWork();
    private static final User USER = new User();
    private static final List<Specialization> SPECIALIZATIONS = new ArrayList<Specialization>();
    private static final Specialization SPECIALIZATION = new Specialization();

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlaceOfWorkService placeOfWorkService;

    @Mock
    private SpecializationService specializationService;

    @Before
    public void setUp() throws Exception {
        SPECIALIZATIONS.add(SPECIALIZATION.setId(1L)
                .setName("НЕЙМ СПЕЦІАЛІЗЕЙШН"));
        SPECIALIZATIONS.add(SPECIALIZATION.setId(2L)
                .setName("НЕЙМ СПЕЦІАЛІЗЕЙШН"));
        USER.setId(USER_ID)
                .setPlaceOfWork(PLACE_OF_WORK.setId(1L)
                        .setName("НЕЙМ ПЛЕЙС ОФ ВОРК"))
                .setSpecializations(SPECIALIZATIONS);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void delete() {
        when(userRepository.findOne(USER_ID)).thenReturn(USER);
    }
}