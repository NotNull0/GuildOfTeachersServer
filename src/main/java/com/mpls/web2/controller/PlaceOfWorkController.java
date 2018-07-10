package com.mpls.web2.controller;

import com.mpls.web2.model.PlaceOfWork;
import com.mpls.web2.service.PlaceOfWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place-of-work")
public class PlaceOfWorkController {

    @Autowired
    private PlaceOfWorkService placeOfWorkService;

    @GetMapping("/find-by-name/{name}")
    private ResponseEntity<PlaceOfWork> findByName(@PathVariable String name) {
        PlaceOfWork placeOfWork;
        if ((placeOfWork = placeOfWorkService.findByName(name)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(placeOfWork);
    }

    @PostMapping("/save")
    private ResponseEntity<PlaceOfWork> save(@RequestBody PlaceOfWork placeOfWork) {
        return ResponseEntity.ok(placeOfWorkService.save(placeOfWork));
    }

    @PostMapping("/update")
    private ResponseEntity<PlaceOfWork> update(@RequestBody PlaceOfWork placeOfWork) {
        return ResponseEntity.ok(placeOfWorkService.update(placeOfWork));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(placeOfWorkService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<PlaceOfWork> findOne(@PathVariable Long id) {
        PlaceOfWork placeOfWork;
        if ((placeOfWork = placeOfWorkService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(placeOfWork);
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<PlaceOfWork>> findAll() {
        List<PlaceOfWork> placeOfWorks = placeOfWorkService.findAll();
        return ResponseEntity.ok(placeOfWorks);
    }


}
