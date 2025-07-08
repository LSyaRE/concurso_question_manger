package com.luminesway.concurso.controllers;

import com.luminesway.concurso.utils.SpringResult;
import com.luminesway.concurso.services.QuestionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/v1/questions")
@Log4j2
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<?> getSoundQuestions() {
       log.info("Getting sound questions");
        SpringResult<?> result = questionService.createQuestions();
        log.info("Returning sound questions");
        return ResponseEntity
                .status(result.getCode())
                .body(result.toJson());
    }
}
