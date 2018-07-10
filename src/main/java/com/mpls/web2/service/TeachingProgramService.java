package com.mpls.web2.service;

import com.mpls.web2.model.TeachingProgram;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeachingProgramService {

    List<TeachingProgram> findAllByContainerId(Long id);

    List<TeachingProgram> findAllAvailable();

    TeachingProgram findOneAvailable(Long id);

    TeachingProgram save(String teachingProgramJson, MultipartFile multipartFile);

    TeachingProgram update(String teachingProgramJson);

    TeachingProgram updateFile(Long id, MultipartFile multipartFile);

    Boolean delete(Long id);

    TeachingProgram findOne(Long id);

    List<TeachingProgram> findAll();

}
