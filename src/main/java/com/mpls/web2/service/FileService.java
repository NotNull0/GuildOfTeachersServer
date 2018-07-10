package com.mpls.web2.service;

import com.mpls.web2.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface FileService {

    File findByName(String name);

    List<File> findByType(Integer type);

    File findOneAvailable(Long id);

    File findOne(Long id);

    File save(MultipartFile multipartFile);

    File save(MultipartFile multipartFile, Principal principal);

    File update(File file);

    File saveForChatMessage(MultipartFile multipartFile, Long id);

    File saveForForum(MultipartFile multipartFile, Long id);

    File update(MultipartFile multipartFile);

    Boolean delete(Long id);

    List<File> findAllAvailable();

    List<File> findAll();

    File findByPath(String path);

}
