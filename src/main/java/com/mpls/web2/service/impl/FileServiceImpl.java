package com.mpls.web2.service.impl;

import com.mpls.web2.model.File;
import com.mpls.web2.model.enums.FileType;
import com.mpls.web2.repository.FileRepository;
import com.mpls.web2.service.ChatMessageService;
import com.mpls.web2.service.FileService;
import com.mpls.web2.service.ForumMessageService;
import com.mpls.web2.service.UserService;
import com.mpls.web2.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.checkId;
import static com.mpls.web2.service.exceptions.Validation.checkString;

@Service
public class FileServiceImpl implements FileService {

    public static final Logger logger = Logger.getLogger(FileServiceImpl.class);

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ForumMessageService forumMessageService;

    @Override
    public File saveForChatMessage(MultipartFile multipartFile, Long id) {
        return fileRepository.save(save(multipartFile).setChatMessage(chatMessageService.findOne(id))
                .setForumMessage(null)
                .setComment(null)
                .setUser(null));
    }

    @Override
    public File saveForForum(MultipartFile multipartFile, Long id) {
        return fileRepository.save(save(multipartFile).setForumMessage(forumMessageService.findOne(id))
                .setComment(null)
                .setUser(null)
                .setChatMessage(null));
    }

    @Override
    public File save(MultipartFile multipartFile, Principal principal) {
        return fileRepository.save(save(multipartFile).setUser(principal != null ? userService.findByEmail(principal.getName()) : null));
    }

    @Override
    public File findByName(String name) {
        checkString(name);
        return fileRepository.findByName(name);
    }

    @Override
    public List<File> findByType(Integer fileTypeNumber) {
        return fileRepository.findAllByFileType(FileType.values()[fileTypeNumber]);
    }

    @Override
    public File findOneAvailable(Long id) {
        checkId(id);
        return fileRepository.findByAvailableAndId(true, id);
    }

    @Override
    public File findOne(Long id) {
        checkId(id);
        return fileRepository.findOne(id);
    }

    @Override
    public File save(MultipartFile multipartFile) {
        return fileRepository.save(new File().setPath(fileBuilder.saveFile(multipartFile))
                .setName(multipartFile.getOriginalFilename())
                .setFileType(fileBuilder.getFileType(multipartFile))
                .setAvailable(true));
    }

    @Override
    public File update(MultipartFile multipartFile) {
        try {
            return save(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public File update(File file) {
        return fileRepository.save(file);
    }

    @Override
    public Boolean delete(Long id) {
        final String basePath = "file:/" + System.getProperty("catalina.home");
        if (id != null && id >= 0) {
            try {
                java.io.File file1 = new java.io.File(basePath + fileRepository.findOne(id).getPath());
                if (file1.delete()) {
                    logger.info("delete File:[" + id + "]");
                } else {
                    logger.info("error delete  File:[" + id + "]");
                }
            } catch (Exception e) {
                logger.error(e);
            }
            fileRepository.delete(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<File> findAllAvailable() {
        return fileRepository.findAllByAvailable(true);
    }

    @Override
    public List<File> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public File findByPath(String path) {
        return fileRepository.findByPath(path);
    }
}
