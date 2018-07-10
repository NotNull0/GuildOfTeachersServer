package com.mpls.web2.service.utils;

import com.mpls.web2.model.enums.FileType;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Prometej on 26.07.2017.
 *
 * @author Prometej
 * @version 1.0
 * @apiNote Class save file and create return path
 * @serial M-Plus
 */
public class FileBuilder {

    private static final Logger logger = Logger.getLogger(FileBuilder.class);

    private final String JPG = "jpg";
    private final String JPEG = "jpeg";
    private final String PNG = "png";
    private final String MP4 = "mp4";
    private final String MP3 = "mp3";

    //    @Value("${base.path.file}")
    private String basePathFile = "/resources/img";

    //    @Value("${base.path.file.name}")
    private String nameFilePath = "/resources/img";
    private String forOthers = "/resources/other";
    private String forVideos = "/resources/video";
    private String forAudio = "/resources/audio";

    private String forMailBody = "/resources/mailBody";
    private String forMailParams = "/resources/mailBody/params";

    /**
     * @param multipartFile
     * @return base path photo
     */

    public String saveFile(MultipartFile multipartFile) {
        String path = "";
        String tag = getFileTeg(multipartFile.getOriginalFilename());
        logger.info("got file with extension : [" + tag + "]");
        switch (tag) {
            case JPG:
                path = nameFilePath;
                break;
            case JPEG:
                path = nameFilePath;
                break;
            case PNG:
                path = nameFilePath;
                break;
            case MP3:
                path = forAudio;
                break;
            case MP4:
                path = forVideos;
                break;
            case "":
                throw new NullPointerException();
            default:
                path = forOthers;
                break;
        }
        return saveFile(multipartFile, path);
    }


    public String saveMailBody(MultipartFile multipartFile) {
        return saveFile(multipartFile, forMailBody);
    }

    public String saveParam(MultipartFile multipartFile) {
        return saveFile(multipartFile, forMailParams);
    }

    public String saveFileInFolder(MultipartFile multipartFile, String folder) {
        return saveFile(multipartFile, "/resources/m-plus/" + folder);
    }

    private String saveFile(MultipartFile multipartFile, String folder) {
        try {
            String tag = getFileTeg(multipartFile.getOriginalFilename());
            String uuid = UUID.randomUUID().toString();
            folder = String.format("%s/%s.%s", folder, uuid, tag);
            File file = new File(System.getProperty("catalina.home") + folder);
            file.getParentFile().mkdirs();//!correct
            if (!file.exists()) {
//                file.createNewFile();
//            Files.setPosixFilePermissions(file.toPath(),
//                    EnumSet.of(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, GROUP_READ, GROUP_EXECUTE));
                multipartFile.transferTo(file);
            } else {

            }
            logger.info("create file->" + multipartFile.getOriginalFilename());
        } catch (IOException e) {
            logger.error("------------path{" + folder + "}-------------------------error file-------------------------------------", e);
            e.printStackTrace();
        }
        return folder;
    }

    public String getFileTeg(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public FileType getFileType(MultipartFile multipartFile) {
        return getFileType(multipartFile.getOriginalFilename());
    }

    public FileType getFileType(String fileName) {
        String tag = getFileTeg(fileName);
        switch (tag) {
            case JPG:
                return FileType.IMAGE;
            case JPEG:
                return FileType.IMAGE;
            case PNG:
                return FileType.IMAGE;
            case MP3:
                return FileType.AUDIO;
            case MP4:
                return FileType.VIDEO;
            default:
                return FileType.OTHER;
        }
    }

    private void loggerError(Object e, String message) {

    }

    private void loggerInfo(String object, String info) {
    }

}