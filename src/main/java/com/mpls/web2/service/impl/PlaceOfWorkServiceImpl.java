package com.mpls.web2.service.impl;

import com.mpls.web2.model.PlaceOfWork;
import com.mpls.web2.repository.PlaceOfWorkRepository;
import com.mpls.web2.service.PlaceOfWorkService;
import com.mpls.web2.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class PlaceOfWorkServiceImpl implements PlaceOfWorkService {

    private static final Logger LOGGER = Logger.getLogger(PlaceOfWorkServiceImpl.class);

    @Autowired
    protected PlaceOfWorkRepository placeOfWorkRepository;

    @Autowired
    private UserService userService;

    @Override
    public PlaceOfWork findByName(String name) {
        checkString(name);
        return placeOfWorkRepository.findByName(name);
    }

    @Override
    public PlaceOfWork save(PlaceOfWork placeOfWork) {
        checkSave(placeOfWork);
        return placeOfWorkRepository.save(placeOfWork.setAvailable(true));
    }

    @Override
    public PlaceOfWork update(PlaceOfWork placeOfWork) {
        checkUpdate(placeOfWork.getId(), placeOfWorkRepository);
        return placeOfWorkRepository.save(findOne(placeOfWork.getId())
                .setName(placeOfWork.getName()));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            PlaceOfWork placeOfWork = placeOfWorkRepository.findOne(id);
            if (placeOfWork != null) {
                placeOfWorkRepository.delete(placeOfWork);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public PlaceOfWork findOne(Long id) {
        checkId(id);
        return placeOfWorkRepository.findOne(id);
    }

    @Override
    public List<PlaceOfWork> findAll() {
        return placeOfWorkRepository.findAll();
    }

    @Override
    public void deleteAllByUsersNull() {
        LOGGER.info("__________POW__________FIND____ALL___________POW___________");
        LOGGER.info(placeOfWorkRepository.findAll());
        LOGGER.info("__________POW__________FIND____ALL___________POW___________");
        placeOfWorkRepository.delete(placeOfWorkRepository.findAllByUsersNull());
    }
}
