package com.mpls.web2.controller;

import com.mpls.web2.dto.FileDto;
import com.mpls.web2.model.File;
import com.mpls.web2.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/save")
    private ResponseEntity<FileDto> save(@RequestBody MultipartFile multipartFile) {
        return ResponseEntity.ok(map(fileService.save(multipartFile), FileDto.class));
    }

    @PostMapping("/save-user")
    private ResponseEntity<FileDto> saveUser(@RequestBody MultipartFile multipartFile, Principal principal){
        return ResponseEntity.ok(map(fileService.save(multipartFile, principal), FileDto.class));
    }

    @PostMapping("/save-forum/{id}")
    private ResponseEntity<FileDto> saveForForum(@RequestBody MultipartFile multipartFile,@PathVariable Long id) {
        return ResponseEntity.ok(map(fileService.saveForForum(multipartFile,id), FileDto.class));
    }

    @PostMapping("/save-chat/{id}")
    private ResponseEntity<FileDto> saveForChatMessage(@RequestBody MultipartFile multipartFile,@PathVariable Long id) {
        return ResponseEntity.ok(map(fileService.saveForChatMessage(multipartFile,id), FileDto.class));
    }

    @GetMapping("/find-by-name/{name}")
    private ResponseEntity<FileDto> findByName(@PathVariable String name) {
        File file = fileService.findByName(name);
        if (file == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(file, FileDto.class));
    }

    @GetMapping("/find-by-type/{fileTypeNumber}")
    private ResponseEntity<List<FileDto>> findByType(@PathVariable Integer fileTypeNumber) {
        List<File> files = fileService.findByType(fileTypeNumber);
        if (files.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(files.stream().map(file -> map(file, FileDto.class)).collect(toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<FileDto> findOneAvailable(@PathVariable Long id){
        File file = fileService.findOneAvailable(id);
        if(file == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(file, FileDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<FileDto> findOne(@PathVariable Long id){
        File file = fileService.findOne(id);
        if(file == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(file, FileDto.class));
    }

    @PutMapping("/update")
    private ResponseEntity<FileDto> update(@RequestBody MultipartFile multipartFile){
        return ResponseEntity.ok(map(fileService.update(multipartFile), FileDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<FileDto>> findAllAvailable(){
        List<File> files = fileService.findAllAvailable();
        if(files.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(files.stream().map(file -> map(file, FileDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<FileDto>> findAll(){
        List<File> files = fileService.findAll();
        return ResponseEntity.ok(files.stream().map(file -> map(file, FileDto.class)).collect(toList()));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.status(fileService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }


}
