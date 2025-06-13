package com.luminesway.concurso.controllers;

import com.luminesway.concurso.core.Result;
import com.luminesway.concurso.dtos.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@RequestMapping("/questions")
public class QuestionController {
    @GetMapping("/")
    public ResponseEntity<?> getSoundQuestions() {
        Result<?> result = Result.success(List.of("Hola"), 404);
        GenericResponse<?> response = result.toJson("todo ok");
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
