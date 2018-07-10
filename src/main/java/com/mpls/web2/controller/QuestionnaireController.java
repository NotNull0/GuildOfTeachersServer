package com.mpls.web2.controller;

import com.mpls.web2.dto.QuestionnaireDto;
import com.mpls.web2.model.Questionnaire;
import com.mpls.web2.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @GetMapping("/find-all")
    private ResponseEntity<List<QuestionnaireDto>> findAll(Principal principal) {
        return ResponseEntity.ok(questionnaireService.findAll(principal).stream()
                .map(questionnaire -> map(questionnaire, QuestionnaireDto.class)).collect(toList()));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<QuestionnaireDto> findOne(@PathVariable Long id, Principal principal) {
        Questionnaire questionnaire = questionnaireService.findOne(id, principal);
        if (questionnaire == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(questionnaire, QuestionnaireDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<QuestionnaireDto> save(@RequestBody QuestionnaireDto questionnaire) {
        return ResponseEntity.ok(map(questionnaireService.save(map(questionnaire, Questionnaire.class)), QuestionnaireDto.class));
    }

    @PutMapping("/update")
    private ResponseEntity<QuestionnaireDto> update(@RequestBody QuestionnaireDto questionnaire) {
        return ResponseEntity.ok(map(questionnaireService.update(map(questionnaire, Questionnaire.class)), QuestionnaireDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(questionnaireService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<QuestionnaireDto>> findAllAvailable(Principal principal) {
        return ResponseEntity.ok(questionnaireService.findAllAvailable(principal).stream()
                .map(questionnaire -> map(questionnaire, QuestionnaireDto.class)).collect(toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<QuestionnaireDto> findOneAvailable(@PathVariable Long id, Principal principal) {
        Questionnaire questionnaire = questionnaireService.findOneAvailable(id, principal);
        if (questionnaire == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(questionnaire, QuestionnaireDto.class));
    }

    @PostMapping("/vote/{questionnaireId}/{courseCategoryId}")
    private ResponseEntity<QuestionnaireDto> vote(@PathVariable Long questionnaireId, @PathVariable Long courseCategoryId, Principal principal) {
        return ResponseEntity.ok(map(questionnaireService.vote(questionnaireId, courseCategoryId, principal), QuestionnaireDto.class));
    }
}
