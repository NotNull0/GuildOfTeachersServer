package com.mpls.web2.service.impl;

import com.mpls.web2.model.File;
import com.mpls.web2.model.TeachingProgram;
import com.mpls.web2.repository.TeachingProgramRepository;
import com.mpls.web2.service.FileService;
import com.mpls.web2.service.TeachingProgramService;
import com.mpls.web2.service.utils.FileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.mpls.web2.config.json.mapper.JsonMapper.json;
import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class TeachingProgramServiceImpl implements TeachingProgramService {
    @Autowired
    private TeachingProgramRepository teachingProgramRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Autowired
    private FileService fileService;

    @Override
    public TeachingProgram updateFile(Long id, MultipartFile multipartFile) {
        TeachingProgram teachingProgram = findOne(id);
        try {
            fileService.delete(teachingProgram.getFile().getId());
            return teachingProgramRepository.save(teachingProgram.setFile(fileService.save(multipartFile)));
        } catch (Exception e) {
            return teachingProgram;
        }
    }

    @Override
    public List<TeachingProgram> findAllByContainerId(Long id) {
        checkId(id);
        return teachingProgramRepository.findAllByContainer_Id(id);
    }

    @Override
    public List<TeachingProgram> findAllAvailable() {
        return teachingProgramRepository.findAllByAvailable(true);
    }

    @Override
    public TeachingProgram findOneAvailable(Long id) {
        checkId(id);
        return teachingProgramRepository.findByAvailableAndId(true, id);
    }

    @Override
    public TeachingProgram save(String teachingProgramJson, MultipartFile multipartFile) {
        checkJson(teachingProgramJson);
        TeachingProgram teachingProgram = json(teachingProgramJson, TeachingProgram.class);
        if (multipartFile != null) {
            File file = fileService.save(multipartFile);
            teachingProgram.setFile(file);
        }
        return teachingProgramRepository.save(teachingProgram);
    }

    @Override
    public TeachingProgram update(String teachingProgramJson) {
        TeachingProgram teachingProgram = json(teachingProgramJson, TeachingProgram.class);
        checkUpdate(teachingProgram.getId(), teachingProgramRepository);
        return teachingProgramRepository.save(findOne(teachingProgram.getId())
                .setName(teachingProgram.getName())
                .setAvailable(teachingProgram.getAvailable())
                .setContainer(teachingProgram.getContainer())
        );
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            TeachingProgram teachingProgram = teachingProgramRepository.findOne(id);
            if (teachingProgram != null) {
                teachingProgramRepository.delete(teachingProgram);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public TeachingProgram findOne(Long id) {
        checkId(id);
        return teachingProgramRepository.findOne(id);
    }

    @Override
    public List<TeachingProgram> findAll() {
        return teachingProgramRepository.findAll();
    }
}
