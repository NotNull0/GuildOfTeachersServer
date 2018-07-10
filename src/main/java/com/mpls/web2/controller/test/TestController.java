package com.mpls.web2.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mpls.web2.model.File;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.mpls.web2.config.json.mapper.JsonMapper.json;
import static com.mpls.web2.config.json.mapper.JsonMapper.jsons;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/saveFileJson")
    private String saveFile(@RequestParam(value = "json") String files, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        return String.valueOf(!multipartFile.isEmpty())+json(files,File.class).getName();
    }
    @PostMapping("/saveFileJson3")
    private String saveFile2(@RequestParam(value = "json") String files, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        return String.valueOf(!multipartFile.isEmpty())+jsons(files,File.class).size();
    }
    @PostMapping("/saveFileJson2")
    private String saveFile2( @RequestBody File files, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        return String.valueOf(!multipartFile.isEmpty())+files.getName();
    }
}
